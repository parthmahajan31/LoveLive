package com.love.lovelive.ui.fragments.home.leader.adapter

import com.love.lovelive.R
import com.love.lovelive.common.DataBindingAdapter
import com.love.lovelive.common.DataBindingViewHolder
import com.love.lovelive.common.DiffCallback
import com.love.lovelive.databinding.LeaderboardItemBinding
import javax.inject.Inject

class LeaderBoardAdapter @Inject constructor() : DataBindingAdapter<String>(DiffCallback(), R.layout.leaderboard_item){

    interface ClickEvent{
        fun onClickItem(position: Int)
    }

    private var clickEvent: ClickEvent? = null

    fun onClick(clickEvent: ClickEvent){
        this.clickEvent = clickEvent
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<String>, position: Int) {
        super.onBindViewHolder(holder, position)
        val binding = holder.getBinding() as LeaderboardItemBinding
        binding.txtSno.text = "${position+1}"

        holder.itemView.setOnClickListener {
            clickEvent?.onClickItem(position)
        }
    }
}