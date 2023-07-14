package com.love.lovelive.ui.fragments.profile

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.love.lovelive.R
import com.love.lovelive.databinding.FragmentProfileBinding
import com.love.lovelive.interactors.ErrorResponse
import com.love.lovelive.interactors.Resource
import com.love.lovelive.interactors.UserUseCase
import com.love.lovelive.ui.fragments.profile.adapter.PostsAdapter
import com.love.lovelive.ui.fragments.profile.adapter.ProfileCategoriesAdapter
import com.love.lovelive.utils.checkErrorResponse
import com.love.lovelive.utils.dismissProgress
import com.love.lovelive.utils.getDummyList
import com.love.lovelive.utils.showProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileVm @Inject constructor(private val useCase: UserUseCase) : ViewModel() {

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
        getProfile()
    }

    private fun getProfile() {
        showProgress(context.requireActivity())
        viewModelScope.launch {
            useCase.getProfile().onEach { dataState->
                when (dataState) {
                    is Resource.Success -> {
                        dismissProgress()

                    }
                    is Resource.Error, is Resource.NetworkError -> {
                        dismissProgress()
                        val error = ErrorResponse(dataState.msg, dataState.success)
                        error.checkErrorResponse(context.requireActivity())
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    fun onClickOtherUser(view:View){
    }
}