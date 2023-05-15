package com.love.lovelive.ui.fragments.getStarted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.love.lovelive.R
import com.love.lovelive.common.BaseFragment
import com.love.lovelive.databinding.FragmentGetStartedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetStartedFragment : BaseFragment<FragmentGetStartedBinding>() {

    private lateinit var binding: FragmentGetStartedBinding
    private val viewModel : GetStartedVm by viewModels()

    override val layoutResourceId: Int
        get() = R.layout.fragment_get_started

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= getViewDataBinding()
        viewModel.initContext(this)
        binding.getStartedVm = viewModel
    }
}