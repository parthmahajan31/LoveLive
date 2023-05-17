package com.love.lovelive.ui.fragments.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.love.lovelive.R
import com.love.lovelive.common.BaseFragment
import com.love.lovelive.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private lateinit var binding: FragmentProfileBinding

    override val layoutResourceId: Int
        get() = R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
    }

}