package com.love.lovelive.ui.activities.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.love.lovelive.R
import com.love.lovelive.databinding.ActivityHomeBinding
import com.love.lovelive.utils.hideIf
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var binding :ActivityHomeBinding
    var navController: NavController? = null
    private val viewModel:HomeActivityVm by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_home)
        viewModel.initContext(this,binding)
        binding.homeActivityVm = viewModel
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.home_nav_host) as NavHostFragment?
        navController = navHostFragment?.navController!!
        navController?.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        binding.bottomNav.hideIf(destination.id !in arrayOf(R.id.homeFragment,R.id.searchFragment,R.id.momentsFragment,R.id.profileFragment))

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            viewModel.handleResult(task)
        }
    }
}