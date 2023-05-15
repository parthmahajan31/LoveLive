package com.love.lovelive.ui.fragments.search

import androidx.lifecycle.ViewModel
import com.love.lovelive.databinding.FragmentSearchBinding
import com.love.lovelive.ui.fragments.search.adapters.SearchCategoriesAdapter
import com.love.lovelive.ui.fragments.search.adapters.SearchLiveAdapter
import com.love.lovelive.ui.fragments.search.adapters.hashtag.HashtagsAdapter
import com.love.lovelive.utils.getDummyList
import com.love.lovelive.utils.gone
import com.love.lovelive.utils.visible
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchVm @Inject constructor() :ViewModel() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var context: SearchFragment

    @Inject
    lateinit var searchCategoriesAdapter: SearchCategoriesAdapter

    @Inject
    lateinit var searchLiveAdapter: SearchLiveAdapter

    @Inject
    lateinit var hashtagsAdapter: HashtagsAdapter

    var categoriesList = arrayListOf("All","Popular","Artist","Hashtag")

    val liveList = getDummyList(10)

    fun initContext(context: SearchFragment){
        this.context = context
        binding = context.getViewDataBinding()

        searchCategoriesAdapter.onClick(object :SearchCategoriesAdapter.ClickEvent{
            override fun onClickItem(position: Int) {
                if(position == 3){
                    binding.liveRv.gone()
                    binding.hashtagRv.visible()
                }
                else{
                    binding.hashtagRv.gone()
                    binding.liveRv.visible()
                }
            }

        })
    }

}