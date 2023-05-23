package com.love.lovelive.ui.fragments.home.leader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.love.lovelive.R
import com.love.lovelive.common.BaseFragment
import com.love.lovelive.databinding.FragmentLeaderBoardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaderBoardFragment : BaseFragment<FragmentLeaderBoardBinding>() {

    private lateinit var binding: FragmentLeaderBoardBinding
    private val viewModel : LeaderBoardVm by viewModels()

    override val layoutResourceId: Int
        get() = R.layout.fragment_leader_board

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getViewDataBinding()
        viewModel.initContext(this)
        binding.leaderboardVm = viewModel
    }

}