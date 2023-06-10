package com.love.lovelive.ui.fragments.signup

import androidx.lifecycle.ViewModel
import com.love.lovelive.databinding.FragmentSignupBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignupVm @Inject constructor() : ViewModel() {

    private lateinit var binding: FragmentSignupBinding
    private lateinit var context:SignupFragment

    fun initContext(context:SignupFragment){
        this.context = context
        binding = context.getViewDataBinding()
    }

}