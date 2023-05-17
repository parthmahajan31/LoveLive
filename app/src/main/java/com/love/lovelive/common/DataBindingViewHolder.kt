package com.love.lovelive.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.library.baseAdapters.BR


open class DataBindingViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binding.setVariable(BR.model,item)
        binding.executePendingBindings()
    }
    fun getBinding() : ViewDataBinding{
        return binding
    }
}