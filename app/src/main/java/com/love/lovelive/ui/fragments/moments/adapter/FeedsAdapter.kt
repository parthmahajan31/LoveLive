package com.love.lovelive.ui.fragments.moments.adapter

import com.love.lovelive.R
import com.love.lovelive.common.DataBindingAdapter
import com.love.lovelive.common.DiffCallback
import javax.inject.Inject

class FeedsAdapter @Inject constructor() :DataBindingAdapter<String>(DiffCallback(), R.layout.feed_item) {

}