package com.love.lovelive.ui.fragments.signup.verify

import android.content.ContentValues.TAG
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.love.lovelive.R
import com.love.lovelive.databinding.FragmentVerifyCodeBinding
import com.love.lovelive.interactors.ErrorResponse
import com.love.lovelive.interactors.Resource
import com.love.lovelive.interactors.UserUseCase
import com.love.lovelive.models.request.LoginSignupRequest
import com.love.lovelive.utils.checkErrorResponse
import com.love.lovelive.utils.dismissProgress
import com.love.lovelive.utils.showProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class VerifyCodeVm @Inject constructor(private val useCase: UserUseCase) : ViewModel() {

    private lateinit var binding: FragmentVerifyCodeBinding
    private lateinit var context: VerifyCodeFragment
    private var phone:String? = null
    private lateinit var storedVerificationId: String
    private lateinit var resendToken:String
    private lateinit var auth: FirebaseAuth

    fun initContext(context: VerifyCodeFragment){
        this.context = context
        binding = context.getViewDataBinding()
        phone = context.arguments?.getString("phone")
        binding.txtPhone.text = phone
        auth = FirebaseAuth.getInstance()

        signup()
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.txtCountDown.text = "00:" + millisUntilFinished / 1000
                // logic to set the EditText could go here
            }

            override fun onFinish() {
                binding.txtCountDown.text = context.getString(R.string.didn_t_receive_sms_try_again)
            }
        }.start()

        binding.etPhone.addTextChangedListener {
            if (it.toString().length == 6){
                val credential = PhoneAuthProvider.getCredential(storedVerificationId, it.toString())
                signInWithPhoneAuthCredential(credential)
            }
        }
    }

    fun onClickBack(view:View){
        context.requireActivity().onBackPressed()
    }

    private fun signup() {
        val options = PhoneAuthOptions.newBuilder()
            .setPhoneNumber(phone.toString()) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(context.requireActivity()) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Timber.e("onVerificationCompleted:" + credential)
//            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Timber.e(e, "onVerificationFailed")

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            } else {
                // reCAPTCHA verification attempted with null Activity
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Timber.e("onCodeSent:" + verificationId)

            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token.toString()
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(context.requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = task.result?.user

                    Timber.e("User->${Gson().toJson(user)}")
                    signupWithPhone()

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    private fun signupWithPhone() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful) {
                val loginSignupRequest = LoginSignupRequest(
                    phoneNumber = context.arguments?.getString("OnlyPhone").toString(),
                    countryCode = context.arguments?.getString("CountryCode")?.toInt(),
                    deviceToken = it.result,
                    deviceType = "ANDROID",
                    isProfileCompleted = false,
                    loginType = "PHONE",
                )
                socialLogin(loginSignupRequest)
            }
        }

    }

    private fun socialLogin(loginSignupRequest: LoginSignupRequest) {
        showProgress(context.requireActivity())
        viewModelScope.launch {
            useCase.loginSignup(loginSignupRequest).onEach { dataState->
                when (dataState) {
                    is Resource.Success -> {
                        dismissProgress()
                        LocalStorage.saveString(context.requireActivity(),"IsLogin","1")
                        LocalStorage.saveToken(dataState.data?.accessToken.toString(),context.requireActivity())
                        context.moveNext(R.id.action_verifyCodeFragment_to_liveFragment)
//                        changeFragment(R.id.liveFragment)
                    }
                    is Resource.Error, is Resource.NetworkError -> {
                        dismissProgress()
                        val error = ErrorResponse(dataState.msg, dataState.success)
                        error.checkErrorResponse(context.requireActivity())
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

}