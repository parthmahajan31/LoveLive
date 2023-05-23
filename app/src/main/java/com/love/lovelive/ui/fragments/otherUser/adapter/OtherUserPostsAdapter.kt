package com.love.lovelive.ui.fragments.otherUser.adapter

import com.love.lovelive.R
import com.love.lovelive.common.DataBindingAdapter
import com.love.lovelive.common.DiffCallback
import javax.inject.Inject

class OtherUserPostsAdapter @Inject constructor() : DataBindingAdapter<String>(DiffCallback(), R.layout.posts_item) {

}