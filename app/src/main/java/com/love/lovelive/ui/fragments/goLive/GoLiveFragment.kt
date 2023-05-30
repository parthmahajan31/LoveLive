package com.love.lovelive.ui.fragments.goLive

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.love.lovelive.R
import com.love.lovelive.common.BaseFragment
import com.love.lovelive.databinding.FragmentGoLiveBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoLiveFragment : BaseFragment<FragmentGoLiveBinding>() {

    private lateinit var binding: FragmentGoLiveBinding
    private val viewModel:GoLiveVm by viewModels()


    override val layoutResourceId: Int
        get() = R.layout.fragment_go_live

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        viewModel.initContext(this)
        binding.goLiveVm = viewModel
    }

}