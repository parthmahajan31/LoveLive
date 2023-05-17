package com.love.lovelive.ui.activities.home

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.NavHostFragment
import com.love.lovelive.R
import com.love.lovelive.databinding.ActivityHomeBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeActivityVm @Inject constructor() : ViewModel() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var context: HomeActivity

    fun initContext(context: HomeActivity,binding: ActivityHomeBinding){
        this.context = context
        this.binding = binding
        onClickHome(binding.home)
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

    private fun changeFragment(id :Int){
        val navHostFragment =
            context.supportFragmentManager.findFragmentById(R.id.home_nav_host) as NavHostFragment?
        context.navController = navHostFragment?.navController!!
        context.navController?.navigate(id)
    }







}