package com.love.lovelive.ui.fragments.search.adapters.hashtag

import com.love.lovelive.R
import com.love.lovelive.common.DataBindingAdapter
import com.love.lovelive.common.DiffCallback
import javax.inject.Inject

class HashtagLiveAdapter @Inject constructor() : DataBindingAdapter<String>( DiffCallback(), R.layout.hashtag_live_item) {
}