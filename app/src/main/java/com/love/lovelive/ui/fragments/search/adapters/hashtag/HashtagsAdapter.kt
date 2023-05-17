package com.love.lovelive.ui.fragments.search.adapters.hashtag

import com.love.lovelive.R
import com.love.lovelive.common.DataBindingAdapter
import com.love.lovelive.common.DataBindingViewHolder
import com.love.lovelive.common.DiffCallback
import com.love.lovelive.databinding.HashtagItemBinding
import com.love.lovelive.utils.getDummyList
import javax.inject.Inject

class HashtagsAdapter @Inject constructor() : DataBindingAdapter<String>(DiffCallback(), R.layout.hashtag_item) {

    override fun onBindViewHolder(holder: DataBindingViewHolder<String>, position: Int) {
        super.onBindViewHolder(holder, position)
        val binding = holder.getBinding() as HashtagItemBinding
        val hashtagLiveAdapter = HashtagLiveAdapter()
        hashtagLiveAdapter.submitList(getDummyList(5))
        binding.hashtagLiveRv.adapter = hashtagLiveAdapter
    }


}