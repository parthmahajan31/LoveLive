package com.love.lovelive.ui.activities.home

import LocalStorage
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import com.love.lovelive.R
import com.love.lovelive.databinding.ActivityHomeBinding
import com.love.lovelive.databinding.SignupPopupBinding
import com.love.lovelive.interactors.ErrorResponse
import com.love.lovelive.interactors.Resource
import com.love.lovelive.interactors.Status
import com.love.lovelive.interactors.UserUseCase
import com.love.lovelive.models.request.LoginSignupRequest
import com.love.lovelive.retrofit.EndPoints
import com.love.lovelive.ui.activities.home.HomeActivity.Companion.gso
import com.love.lovelive.utils.CommonAlertDialog
import com.love.lovelive.utils.checkErrorResponse
import com.love.lovelive.utils.dismissProgress
import com.love.lovelive.utils.showProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomeActivityVm @Inject constructor(private val useCase: UserUseCase) : ViewModel() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var context: HomeActivity
    private var isLogin:Boolean? = false
    val RC_SIGN_IN = 123
    private lateinit var mGoogleSignInClient: GoogleSignInClient



    fun initContext(context: HomeActivity,binding: ActivityHomeBinding){
        this.context = context
        this.binding = binding
        onClickHome(binding.home)
        FirebaseApp.initializeApp(context)

        mGoogleSignInClient = GoogleSignIn.getClient(context,gso)
        isLogin = !LocalStorage.getStringValue(context,"IsLogin").isNullOrEmpty()
    }

    fun onClickSearch(view:View){
       changeFragment(R.id.searchFragment)
        binding.imgSearch.setColorFilter(ContextCompat.getColor(context, R.color.default_pink), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtSearch.setTextColor(ContextCompat.getColor(context,R.color.default_pink))
        binding.imgHome.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtHome.setTextColor(ContextCompat.getColor(context,R.color.black))
        binding.imgMoments.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtMoments.setTextColor(ContextCompat.getColor(context,R.color.black))
        binding.imgProfile.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtProfile.setTextColor(ContextCompat.getColor(context,R.color.black))
    }

    fun onClickHome(view: View){
        changeFragment(R.id.homeFragment)
        binding.imgSearch.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtSearch.setTextColor(ContextCompat.getColor(context,R.color.black))
        binding.imgHome.setColorFilter(ContextCompat.getColor(context, R.color.default_pink), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtHome.setTextColor(ContextCompat.getColor(context,R.color.default_pink))
        binding.imgMoments.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtMoments.setTextColor(ContextCompat.getColor(context,R.color.black))
        binding.imgProfile.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtProfile.setTextColor(ContextCompat.getColor(context,R.color.black))
    }

    fun onClickMoments(view:View){
        changeFragment(R.id.momentsFragment)
        binding.imgHome.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtHome.setTextColor(ContextCompat.getColor(context,R.color.black))
        binding.imgMoments.setColorFilter(ContextCompat.getColor(context, R.color.default_pink), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtMoments.setTextColor(ContextCompat.getColor(context,R.color.default_pink))
        binding.imgSearch.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtSearch.setTextColor(ContextCompat.getColor(context,R.color.black))
        binding.imgProfile.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtProfile.setTextColor(ContextCompat.getColor(context,R.color.black))
    }

    fun onClickProfile(view: View){
        changeFragment(R.id.profileFragment)
        binding.imgSearch.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtSearch.setTextColor(ContextCompat.getColor(context,R.color.black))
        binding.imgProfile.setColorFilter(ContextCompat.getColor(context, R.color.default_pink), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtProfile.setTextColor(ContextCompat.getColor(context,R.color.default_pink))
        binding.imgMoments.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtMoments.setTextColor(ContextCompat.getColor(context,R.color.black))
        binding.imgHome.setColorFilter(ContextCompat.getColor(context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
        binding.txtHome.setTextColor(ContextCompat.getColor(context,R.color.black))
    }

    fun onClickGoLive(view: View){
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, "android.permission.CAMERA") != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(context, "android.permission.RECORD_AUDIO") != PackageManager.PERMISSION_GRANTED) {
                context.requestPermissions(context.permissionNeeded, 101)
            }
            else{
                changeFragment(R.id.liveFragment)
            }
        }*/

        if (isLogin == true){
            changeFragment(R.id.goLiveFragment)
        }
        else{
            showSignupPopup()
        }

    }

    private fun showSignupPopup() {
        val dialog = BottomSheetDialog(context)
        val dialogBinding = SignupPopupBinding.inflate(dialog.layoutInflater)
        dialog.setCancelable(true)
        dialog.setContentView(dialogBinding.root)
        dialog.show()
        dialogBinding.googleSignInBtn.setOnClickListener {
            dialog.dismiss()
            signInGoogle()
        }
        dialogBinding.txtSms.setOnClickListener {
            dialog.dismiss()
            changeFragment(R.id.signupFragment)
        }
    }

    private fun signInGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        context.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun changeFragment(id :Int){
        val navHostFragment =
            context.supportFragmentManager.findFragmentById(R.id.home_nav_host) as NavHostFragment?
        context.navController = navHostFragment?.navController!!
        context.navController?.navigate(id)
    }

    fun handleSignInResult(task: Task<GoogleSignInAccount>?) {
        try {
            val account = task?.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            // You can access the user's ID, email, display name, etc. from the account object.
            val userId = account?.id
            val email = account?.email
            val displayName = account?.displayName
            val profilePic = account?.photoUrl

            Timber.e("Data->$userId,$email,$displayName")
            FirebaseMessaging.getInstance().token.addOnCompleteListener {
                if (it.isSuccessful) {
                    Timber.e("FIREBASE_TOKEN----- ${it.result}")
                    val loginRequest = LoginSignupRequest(
                        loginType = "GOOGLE",
                        deviceType = "ANDROID",
                        deviceToken = it.result,
                        isProfileCompleted = true,
                        socialId = userId.toString(),
                        name = displayName.toString(),
                        email = email.toString(),
                        profilePic = profilePic.toString()
                    )
                    socialLogin(loginRequest)
                } else {
                    Timber.e("FIREBASE_TOKEN_not_found----- ${it.result}")
                    val error = ErrorResponse("Something went wrong, try again later", Status.ERROR)
                    error.checkErrorResponse(context)
                }
            }

            /*CommonAlertDialog(context,"GoogleData->\nEmail->${email}\nDisplay Name->${displayName}","Ok","",object :CommonAlertDialog.OnButtonClickListener{
                override fun onPositiveButtonClicked() {

                }

                override fun onNegativeButtonClicked() {

                }

            }).show()*/
            // ...
        } catch (e: ApiException) {
            // Sign-in failed, handle the error.
            // ...
            Timber.e("Exception->${e.message}")
        }
    }

    private fun socialLogin(loginSignupRequest: LoginSignupRequest) {
        showProgress(context)
        viewModelScope.launch {
            useCase.loginSignup(loginSignupRequest).onEach { dataState->
                when (dataState) {
                    is Resource.Success -> {
                        dismissProgress()
                        LocalStorage.saveString(context,"IsLogin","1")
                        LocalStorage.saveToken(dataState.data?.accessToken.toString(),context)
                        changeFragment(R.id.liveFragment)
                    }
                    is Resource.Error, is Resource.NetworkError -> {
                        dismissProgress()
                        val error = ErrorResponse(dataState.msg, dataState.success)
                        error.checkErrorResponse(context)
                    }
                }
            }.launchIn(viewModelScope)
        }
    }


}