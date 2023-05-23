package com.love.lovelive.ui.fragments.profile.adapter

import androidx.core.content.ContextCompat
import com.love.lovelive.R
import com.love.lovelive.common.DataBindingAdapter
import com.love.lovelive.common.DataBindingViewHolder
import com.love.lovelive.common.DiffCallback
import com.love.lovelive.databinding.ProfileCategoriesItemBinding
import com.love.lovelive.utils.gone
import com.love.lovelive.utils.visible
import javax.inject.Inject

class ProfileCategoriesAdapter @Inject constructor(): DataBindingAdapter<String>(DiffCallback(), R.layout.profile_categories_item) {

    var index = 0

    override fun onBindViewHolder(holder: DataBindingViewHolder<String>, position: Int) {
        super.onBindViewHolder(holder, position)

        val  binding = holder.getBinding() as ProfileCategoriesItemBinding

        binding.root.setOnClickListener {
            index = position
            notifyDataSetChanged()
        }

        if (index == position){
            binding.imgCat.setImageResource(R.drawable.baseline_camera_alt_24)
            binding.imgCat.setColorFilter(ContextCompat.getColor(binding.root.context, R.color.black), android.graphics.PorterDuff.Mode.SRC_IN)
            binding.txtCat.setTextColor(binding.root.context.resources.getColor(R.color.black))
            binding.selectedView.visible()
        }
        else{
            binding.imgCat.setImageResource(R.drawable.picture_camera_icon)
            binding.imgCat.setColorFilter(ContextCompat.getColor(binding.root.context, `in`.aabhasjindal.otptextview.R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN)
            binding.txtCat.setTextColor(binding.root.context.resources.getColor(`in`.aabhasjindal.otptextview.R.color.grey))
            binding.selectedView.gone()
        }
    }


}