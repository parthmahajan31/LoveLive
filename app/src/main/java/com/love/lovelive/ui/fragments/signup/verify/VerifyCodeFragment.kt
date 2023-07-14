package com.love.lovelive.ui.fragments.signup.verify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.love.lovelive.R
import com.love.lovelive.common.BaseFragment
import com.love.lovelive.databinding.FragmentVerifyCodeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VerifyCodeFragment : BaseFragment<FragmentVerifyCodeBinding>() {

    private lateinit var binding: FragmentVerifyCodeBinding
    private val viewModel:VerifyCodeVm by viewModels()

    override val layoutResourceId: Int
        get() = R.layout.fragment_verify_code

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        viewModel.initContext(this)
        binding.verifyCodeVm = viewModel
    }

}