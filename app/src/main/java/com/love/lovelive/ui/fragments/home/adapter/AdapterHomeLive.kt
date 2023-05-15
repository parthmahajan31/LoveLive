package com.love.lovelive.ui.fragments.home.adapter

import com.love.lovelive.R
import com.love.lovelive.common.DataBindingAdapter
import com.love.lovelive.common.DataBindingViewHolder
import com.love.lovelive.common.DiffCallback
import com.love.lovelive.databinding.LiveItemBinding
import javax.inject.Inject

class AdapterHomeLive @Inject constructor() : DataBindingAdapter<String>(DiffCallback(), R.layout.live_item) {

    override fun onBindViewHolder(holder: DataBindingViewHolder<String>, position: Int) {
        super.onBindViewHolder(holder, position)
        val binding = holder.getBinding() as LiveItemBinding

        if (position % 3 == 0){
            binding.imgLive.setImageResource(R.drawable.place_holder_1)
            binding.imgUser.setImageResource(R.drawable.rectangle)
        }
    }


}