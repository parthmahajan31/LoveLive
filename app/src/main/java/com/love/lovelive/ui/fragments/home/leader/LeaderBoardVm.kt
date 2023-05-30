package com.love.lovelive.ui.fragments.home.leader

import android.graphics.Typeface
import android.view.View
import androidx.lifecycle.ViewModel
import com.love.lovelive.R
import com.love.lovelive.databinding.FragmentLeaderBoardBinding
import com.love.lovelive.ui.fragments.home.leader.adapter.LeaderBoardAdapter
import com.love.lovelive.utils.getDummyList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeaderBoardVm @Inject constructor() : ViewModel() {

    private lateinit var binding: FragmentLeaderBoardBinding
    private lateinit var context: LeaderBoardFragment

    @Inject
    lateinit var leaderBoardAdapter: LeaderBoardAdapter

    val list = getDummyList(10)

    fun initContext(context:LeaderBoardFragment){
        this.context = context
        binding = context.getViewDataBinding()
        onClickCreators(binding.txtCreators)
        onClickLive(binding.txtLive)


        leaderBoardAdapter.onClick(object :LeaderBoardAdapter.ClickEvent{
            override fun onClickItem(position: Int) {
                context.moveNext(R.id.action_leaderBoardFragment_to_otherUserProfileFragment)
            }

        })
    }

    fun onClickCreators(view: View){
        binding.txtCreators.setTextColor(context.resources.getColor(R.color.black))
        binding.txtFamilies.setTextColor(context.resources.getColor(`in`.aabhasjindal.otptextview.R.color.grey))
    }

    fun onClickFamilies(view: View){
        binding.txtCreators.setTextColor(context.resources.getColor(`in`.aabhasjindal.otptextview.R.color.grey))
        binding.txtFamilies.setTextColor(context.resources.getColor(R.color.black))

    }

    fun onClickLive(view: View){
        binding.txtLive.setTextColor(context.resources.getColor(R.color.black))
        binding.txtLive.setTypeface(binding.txtLive.typeface, Typeface.BOLD)
        binding.txtLive.background = context.resources.getDrawable(R.drawable.creators_live_bg)
        binding.txtDaily.setTextColor(context.resources.getColor(`in`.aabhasjindal.otptextview.R.color.grey))
        binding.txtDaily.background = null
        binding.txtAllTime.setTextColor(context.resources.getColor(`in`.aabhasjindal.otptextview.R.color.grey))
        binding.txtAllTime.background = null


    }
    fun onClickDaily(view: View){
        binding.txtDaily.setTextColor(context.resources.getColor(R.color.black))
        binding.txtDaily.setTypeface(binding.txtLive.typeface, Typeface.BOLD)
        binding.txtDaily.background = context.resources.getDrawable(R.drawable.creators_live_bg)
        binding.txtLive.setTextColor(context.resources.getColor(`in`.aabhasjindal.otptextview.R.color.grey))
        binding.txtLive.background = null
        binding.txtAllTime.setTextColor(context.resources.getColor(`in`.aabhasjindal.otptextview.R.color.grey))
        binding.txtAllTime.background = null

    }
    fun onClickAllTime(view: View){
        binding.txtAllTime.setTextColor(context.resources.getColor(R.color.black))
        binding.txtAllTime.setTypeface(binding.txtLive.typeface, Typeface.BOLD)
        binding.txtAllTime.background = context.resources.getDrawable(R.drawable.creators_live_bg)
        binding.txtDaily.setTextColor(context.resources.getColor(`in`.aabhasjindal.otptextview.R.color.grey))
        binding.txtDaily.background = null
        binding.txtLive.setTextColor(context.resources.getColor(`in`.aabhasjindal.otptextview.R.color.grey))
        binding.txtLive.background = null

    }
}