package com.love.lovelive.ui.fragments.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.love.lovelive.R
import com.love.lovelive.common.BaseFragment
import com.love.lovelive.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>() {

    private lateinit var binding: FragmentSignupBinding
    private val viewModel:SignupVm by viewModels()

    override val layoutResourceId: Int
        get() =R.layout.fragment_signup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        viewModel.initContext(this)
        binding.signupVm = viewModel
    }
}