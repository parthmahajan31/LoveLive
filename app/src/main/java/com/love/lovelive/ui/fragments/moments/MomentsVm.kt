package com.love.lovelive.ui.fragments.moments

import androidx.lifecycle.ViewModel
import com.love.lovelive.databinding.FragmentMomentsBinding
import com.love.lovelive.ui.fragments.moments.adapter.FeedsAdapter
import com.love.lovelive.ui.fragments.moments.adapter.MomentsCategoriesAdapter
import com.love.lovelive.utils.getDummyList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MomentsVm @Inject constructor() : ViewModel()  {

    private lateinit var binding:FragmentMomentsBinding
    private lateinit var context:MomentsFragment

    @Inject
    lateinit var momentsCategoriesAdapter: MomentsCategoriesAdapter

    @Inject
    lateinit var feedsAdapter: FeedsAdapter

    val categoriesList = arrayListOf("All","For Fans")

    val feedList = getDummyList(8)

    fun initContext(context:MomentsFragment){
        this.context = context
        binding = context.getViewDataBinding()
    }

}