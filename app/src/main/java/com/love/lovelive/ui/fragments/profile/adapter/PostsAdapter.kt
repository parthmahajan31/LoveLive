package com.love.lovelive.ui.fragments.profile.adapter

import com.love.lovelive.R
import com.love.lovelive.common.DataBindingAdapter
import com.love.lovelive.common.DiffCallback
import javax.inject.Inject

class PostsAdapter @Inject constructor() :DataBindingAdapter<String>(DiffCallback(), R.layout.posts_item) {


}