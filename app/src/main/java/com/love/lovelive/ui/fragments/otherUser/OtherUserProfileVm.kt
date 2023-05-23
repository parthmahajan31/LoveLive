package com.love.lovelive.ui.fragments.otherUser

import android.view.View
import androidx.lifecycle.ViewModel
import com.love.lovelive.databinding.FragmentOtherUserProfileBinding
import com.love.lovelive.ui.fragments.otherUser.adapter.AdapterOtherUserCategories
import com.love.lovelive.ui.fragments.otherUser.adapter.OtherUserPostsAdapter
import com.love.lovelive.utils.getDummyList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OtherUserProfileVm @Inject constructor(): ViewModel() {

    private lateinit var binding: FragmentOtherUserProfileBinding
    private lateinit var context: OtherUserProfileFragment

    @Inject
    lateinit var otherUserCategories: AdapterOtherUserCategories

    @Inject
    lateinit var otherUserPostsAdapter: OtherUserPostsAdapter

    val categoriesList = arrayListOf("All","Moments","Lovelive Cards")

    val list = getDummyList(10)

    fun initContext(context: OtherUserProfileFragment){
        this.context = context
        binding = context.getViewDataBinding()
    }

    fun onClickBack(view:View){
        context.requireActivity().onBackPressed()
    }

}