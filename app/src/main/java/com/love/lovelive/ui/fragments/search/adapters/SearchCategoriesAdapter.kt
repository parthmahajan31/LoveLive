package com.love.lovelive.ui.fragments.search.adapters

import com.love.lovelive.R
import com.love.lovelive.common.DataBindingAdapter
import com.love.lovelive.common.DataBindingViewHolder
import com.love.lovelive.common.DiffCallback
import com.love.lovelive.databinding.CategoriesItemBinding
import com.love.lovelive.utils.gone
import com.love.lovelive.utils.visible
import javax.inject.Inject

class SearchCategoriesAdapter @Inject constructor() : DataBindingAdapter<String>(DiffCallback(), R.layout.categories_item) {

    interface ClickEvent{
        fun onClickItem(position: Int)
    }

    private var clickEvent:ClickEvent? = null

    fun onClick(clickEvent: ClickEvent){
        this.clickEvent = clickEvent
    }

    var index = 0

    override fun onBindViewHolder(holder: DataBindingViewHolder<String>, position: Int) {
        super.onBindViewHolder(holder, position)
        val binding = holder.getBinding() as CategoriesItemBinding
        holder.itemView.setOnClickListener {
            index = position
            notifyDataSetChanged()
        }

        if (index == position){
            clickEvent?.onClickItem(position)
            binding.selectedView.visible()
            binding.txtCat.setTextColor(binding.root.context.resources.getColor(R.color.default_pink))
        }
        else{
            binding.selectedView.gone()
            binding.txtCat.setTextColor(binding.root.context.resources.getColor(R.color.black))
        }
    }


}