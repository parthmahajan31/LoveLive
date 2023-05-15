package com.love.lovelive.common

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class BasePagerAdapter(
    var fragment: Fragment,
    var items: Array<BaseFragment<out ViewDataBinding>>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = items.size
    override fun createFragment(position: Int): Fragment {
        return items[position]
    }
}