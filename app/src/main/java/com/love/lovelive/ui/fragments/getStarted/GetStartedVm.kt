package com.love.lovelive.ui.fragments.getStarted

import LocalStorage
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.love.lovelive.R
import com.love.lovelive.databinding.FragmentGetStartedBinding
import com.love.lovelive.ui.activities.home.HomeActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GetStartedVm @Inject constructor() :ViewModel() {

    private lateinit var binding: FragmentGetStartedBinding
    private lateinit var context :GetStartedFragment

    fun initContext(context:GetStartedFragment){
        this.context = context
        binding = context.getViewDataBinding()

        if (!LocalStorage.getStringValue(context.requireActivity(),"Started").isNullOrEmpty()){
            context.startActivity(Intent(context.requireActivity(),HomeActivity::class.java))
        }
    }

    fun onClickSkip(view:View){
        LocalStorage.saveString(context.requireActivity(),"Started","Done")
        context.startActivity(Intent(context.requireActivity(),HomeActivity::class.java))
    }

}