package com.love.lovelive.ui.fragments.moments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.love.lovelive.R
import com.love.lovelive.common.BaseFragment
import com.love.lovelive.databinding.FragmentMomentsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MomentsFragment : BaseFragment<FragmentMomentsBinding>() {

    private lateinit var binding: FragmentMomentsBinding
    private val viewModel:MomentsVm by viewModels()

    override val layoutResourceId: Int
        get() = R.layout.fragment_moments

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        viewModel.initContext(this)
        binding.momentsVm = viewModel

    }

}