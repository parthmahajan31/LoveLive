package com.love.lovelive.ui.fragments.signup

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.love.lovelive.R
import com.love.lovelive.databinding.FragmentSignupBinding
import com.love.lovelive.ui.activities.home.HomeActivity
import com.love.lovelive.ui.activities.home.HomeActivity.Companion.gso
import com.love.lovelive.utils.alert
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class SignupVm @Inject constructor() : ViewModel() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var context:SignupFragment
    val RC_SIGN_IN = 123
    private lateinit var mGoogleSignInClient: GoogleSignInClient


    fun initContext(context:SignupFragment){
        this.context = context
        binding = context.getViewDataBinding()

        mGoogleSignInClient = GoogleSignIn.getClient(context.requireActivity(),gso)
    }

    fun onClickNext(view: View){
        if (binding.etPhone.text.isNullOrEmpty()){
            "Please enter phone number".alert(context.requireActivity())

        }
        else if (binding.etPhone.text.length < 10){
            "Please enter valid phone number".alert(context.requireActivity())
        }
        else{
            val bundle = Bundle()
            bundle.putString("phone","${binding.ccp.selectedCountryCodeWithPlus} ${binding.etPhone.text}")
            bundle.putString("CountryCode","${binding.ccp.selectedCountryCode}")
            bundle.putString("OnlyPhone","${binding.etPhone.text}")
            context.moveNextArgs(R.id.action_signupFragment_to_verifyCodeFragment,bundle)
        }
    }




    fun onClickGoogleSignup(view: View){
        val signInIntent = mGoogleSignInClient.signInIntent
        context.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun onClickBack(view: View){
        context.requireActivity().onBackPressed()
    }


}