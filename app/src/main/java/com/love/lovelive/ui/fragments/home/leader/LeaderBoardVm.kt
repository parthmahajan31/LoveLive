package com.love.lovelive.ui.fragments.home.leader

import android.view.View
import androidx.lifecycle.ViewModel
import com.love.lovelive.R
import com.love.lovelive.databinding.FragmentLeaderBoardBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeaderBoardVm @Inject constructor() : ViewModel() {

    private lateinit var binding: FragmentLeaderBoardBinding
    private lateinit var context: LeaderBoardFragment

    fun initContext(context:LeaderBoardFragment){
        this.context = context
        binding = context.getViewDataBinding()
    }

    fun onClickCreators(view: View){

        binding.txtCreators.setTextColor(context.resources.getColor(R.color.black))
        binding.txtFamilies.setTextColor(context.resources.getColor(`in`.aabhasjindal.otptextview.R.color.grey))

    }

    fun onClickFamilies(view: View){
        binding.txtCreators.setTextColor(context.resources.getColor(`in`.aabhasjindal.otptextview.R.color.grey))
        binding.txtFamilies.setTextColor(context.resources.getColor(R.color.black))

    }
}