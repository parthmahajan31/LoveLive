package com.love.lovelive.ui.activities.home

import LocalStorage
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.love.lovelive.R
import com.love.lovelive.databinding.ActivityHomeBinding
import com.love.lovelive.databinding.SignupPopupBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomeActivityVm @Inject constructor() : ViewModel() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var context: HomeActivity
    private var isLogin:Boolean? = false
    lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth



    fun initContext(context: HomeActivity,binding: ActivityHomeBinding){
        this.context = context
        this.binding = binding
        onClickHome(binding.home)
        FirebaseApp.initializeApp(context)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(context, gso)
        firebaseAuth = FirebaseAuth.getInstance()
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
            signInGoogle()
        }
    }

    private fun signInGoogle() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        context.startActivityForResult(signInIntent, 100)
    }

    private fun changeFragment(id :Int){
        val navHostFragment =
            context.supportFragmentManager.findFragmentById(R.id.home_nav_host) as NavHostFragment?
        context.navController = navHostFragment?.navController!!
        context.navController?.navigate(id)
    }

    fun handleResult(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
            if (account != null) {
                UpdateUI(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun UpdateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Timber.e("Email->${account.email.toString()}")
                Timber.e("Username->${account.displayName.toString()}")
            }
        }
    }


}