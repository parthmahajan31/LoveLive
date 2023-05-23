package com.love.lovelive.ui.fragments.profile

import android.view.View
import androidx.lifecycle.ViewModel
import com.love.lovelive.R
import com.love.lovelive.databinding.FragmentProfileBinding
import com.love.lovelive.ui.fragments.profile.adapter.PostsAdapter
import com.love.lovelive.ui.fragments.profile.adapter.ProfileCategoriesAdapter
import com.love.lovelive.utils.getDummyList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileVm @Inject constructor() : ViewModel() {

    private lateinit var binding:FragmentProfileBinding
    private lateinit var context:ProfileFragment

    @Inject
    lateinit var profileCategoriesAdapter: ProfileCategoriesAdapter

    @Inject
    lateinit var postsAdapter: PostsAdapter

    val list = getDummyList(10)

    val categoriesList = arrayListOf("All","For Fans","Moments","Lovelive Cards","Collections")

    fun initContext(context:ProfileFragment){
        this.context = context
        binding = context.getViewDataBinding()
    }

    fun onClickOtherUser(view:View){
        context.moveNext(R.id.action_profileFragment_to_otherUserProfileFragment)
    }
}