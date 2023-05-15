package com.love.lovelive.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.love.lovelive.R
import com.love.lovelive.common.BaseFragment
import com.love.lovelive.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel:HomeVm by viewModels()

    override val layoutResourceId: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        viewModel.initContext(this)
        binding.homeVm = viewModel
    }

}