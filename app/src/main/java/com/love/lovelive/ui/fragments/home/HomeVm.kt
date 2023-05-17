package com.love.lovelive.ui.fragments.home

import android.view.View
import androidx.lifecycle.ViewModel
import com.love.lovelive.R
import com.love.lovelive.databinding.FragmentHomeBinding
import com.love.lovelive.ui.fragments.home.adapter.AdapterHomeLive
import com.love.lovelive.utils.getDummyList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVm @Inject constructor() :ViewModel() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var context:HomeFragment

    @Inject
    lateinit var adapterHomeLive: AdapterHomeLive

    val list = getDummyList(8)



    fun initContext(context:HomeFragment){
        this.context = context
        binding = context.getViewDataBinding()
        onClickNear(binding.txtNear)
    }

    fun onClickNear(view:View){
        binding.txtNear.setTextColor(context.resources.getColor(R.color.default_pink))
        binding.txtFollow.setTextColor(context.resources.getColor(R.color.black))
        binding.txtForYou.setTextColor(context.resources.getColor(R.color.black))
    }

    fun onClickFollow(view: View){
        binding.txtFollow.setTextColor(context.resources.getColor(R.color.default_pink))
        binding.txtNear.setTextColor(context.resources.getColor(R.color.black))
        binding.txtForYou.setTextColor(context.resources.getColor(R.color.black))
    }

    fun onClickForYou(view: View){
        binding.txtForYou.setTextColor(context.resources.getColor(R.color.default_pink))
        binding.txtNear.setTextColor(context.resources.getColor(R.color.black))
        binding.txtFollow.setTextColor(context.resources.getColor(R.color.black))
    }


}