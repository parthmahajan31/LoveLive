package com.love.lovelive.ui.activities.home

import LocalStorage
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.FirebaseApp
import com.love.lovelive.R
import com.love.lovelive.databinding.ActivityHomeBinding
import com.love.lovelive.databinding.SignupPopupBinding
import com.love.lovelive.utils.CommonAlertDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomeActivityVm @Inject constructor() : ViewModel() {

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
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
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

            Timber.e("Data->$userId,$email,$displayName")
            CommonAlertDialog(context,"GoogleData->\nEmail->${email}\nDisplay Name->${displayName}","Ok","",object :CommonAlertDialog.OnButtonClickListener{
                override fun onPositiveButtonClicked() {

                }

                override fun onNegativeButtonClicked() {

                }

            }).show()
            // ...
        } catch (e: ApiException) {
            // Sign-in failed, handle the error.
            // ...
            Timber.e("Exception->${e.message}")
        }
    }


}