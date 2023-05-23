package com.love.lovelive.ui.fragments.otherUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.love.lovelive.R
import com.love.lovelive.common.BaseFragment
import com.love.lovelive.databinding.FragmentOtherUserProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtherUserProfileFragment : BaseFragment<FragmentOtherUserProfileBinding>() {

    private lateinit var binding: FragmentOtherUserProfileBinding
    private val viewModel:OtherUserProfileVm by viewModels()

    override val layoutResourceId: Int
        get() = R.layout.fragment_other_user_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        viewModel.initContext(this)
        binding.otherUserProfileVm = viewModel
    }

}