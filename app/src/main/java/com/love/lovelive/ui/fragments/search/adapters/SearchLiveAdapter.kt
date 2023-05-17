package com.love.lovelive.ui.fragments.search.adapters

import com.love.lovelive.R
import com.love.lovelive.common.DataBindingAdapter
import com.love.lovelive.common.DiffCallback
import javax.inject.Inject

class SearchLiveAdapter @Inject constructor() : DataBindingAdapter<String>(DiffCallback(), R.layout.live_item) {


}