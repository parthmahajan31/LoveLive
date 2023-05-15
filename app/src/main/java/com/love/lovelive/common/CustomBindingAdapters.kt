package com.love.lovelive.common

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableBoolean
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener

import android.widget.SeekBar
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.love.lovelive.R
import com.google.android.material.tabs.TabLayout
import com.makeramen.roundedimageview.RoundedImageView


object CustomBindingAdapters {

    /**
     * Set Recyclerview Adapter
     */
    @BindingAdapter(value = ["setRecyclerviewAdapter","itemList"],requireAll = false)
    @JvmStatic
    fun <T>setRecyclerviewAdapter(recyclerView: RecyclerView?, adapter: DataBindingAdapter<T>?, list: ArrayList<T>?){
        recyclerView?.adapter = adapter
        adapter?.submitList(list)
    }


    @BindingAdapter(value = ["setToolBarColor"],requireAll = false)
    @JvmStatic
    fun setToolBarColor(constraintLayout: ConstraintLayout,boolean: ObservableBoolean){
        if (boolean.get())
            constraintLayout.setBackgroundColor(ContextCompat.getColor((constraintLayout.context),
                R.color.black))
        else
            constraintLayout.setBackgroundColor(ContextCompat.getColor((constraintLayout.context),R.color.white))
    }

  /*  @BindingAdapter(value = ["setHomeBackground"],requireAll = false)
    @JvmStatic
    fun setHomeBackground(constraintLayout: ConstraintLayout,boolean: ObservableBoolean){
        if (boolean.get())
            constraintLayout.background = (ContextCompat.getDrawable((constraintLayout.context),R.drawable.login_shape))
        else
            constraintLayout.setBackgroundColor(ContextCompat.getColor((constraintLayout.context),R.color.white))
    }*/

    /**
     * Set Navigation Drawer Animation
     */
    @BindingAdapter(value = ["setDrawerAnimation"],requireAll = false)
    @JvmStatic
    fun setDrawerAnimation(drawerLayout: DrawerLayout,constraintLayout: ConstraintLayout){
        drawerLayout.addDrawerListener(object : SimpleDrawerListener() {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {

                // Scale the View based on current slide offset
                val diffScaledOffset: Float = slideOffset * (1 - 0.7f)
                val offsetScale = 1 - diffScaledOffset
                constraintLayout.scaleX = offsetScale
                constraintLayout.scaleY = offsetScale

                // Translate the View, accounting for the scaled width
                val xOffset: Float = constraintLayout.width * slideOffset
                val xOffsetDiff: Float = constraintLayout.width * diffScaledOffset / 2
                val xTranslation = xOffset - xOffsetDiff
                constraintLayout.translationX = xTranslation
            }
        })
    }

    @BindingAdapter(value = ["seekbarListener"],requireAll = false)
    @JvmStatic
    fun seekbarListener(seekBar: SeekBar?,seekBarChangeListener: SeekBar.OnSeekBarChangeListener?){
        seekBar?.let {
            it.setOnSeekBarChangeListener(seekBarChangeListener)
        }
    }

 /*   *//**
     * Set view Pager with Tabs
     *//*

    @BindingAdapter(value = ["setViewPagerTabs","tabNames","fragmentName","fragmentsItems","isScrollableSet"],requireAll = false)
    @JvmStatic
    fun setViewPagerAdapter(viewPager2: ViewPager2?, tabLayout: TabLayout?, items:ArrayList<String>?, fragment:Fragment?, fragmentsItems:Array<BaseFragment<out ViewDataBinding>>?, isScroll:Boolean?){

           items?.let {
               for (element in it) {
                   tabLayout?.addTab(tabLayout.newTab().setText(element))
               }
               viewPager2?.adapter = BasePagerAdapter(fragment!!, fragmentsItems!!)
               isScroll?.let { boolean ->
                   viewPager2?.isUserInputEnabled = boolean
               }
               attachViewPager(tabLayout!!, viewPager2!!, it)
           }

    }*/

    /*@BindingAdapter(value = ["setNotificationIcon"],requireAll = false)
    @JvmStatic
    fun setNotificationIcon(imageView: ImageView,value:String){
        when(value){
            "Fedrel Refund"->{
                imageView.setImageResource(R.drawable.ic_blue_notification)
            }
            "State Refund"->{
                imageView.setImageResource(R.drawable.ic_green_notification)

            }
            "Refund Anticipation Loan"->{
                imageView.setImageResource(R.drawable.ic_yellow_notification)

            }
            "Auto Title Loan"->{
                imageView.setImageResource(R.drawable.ic_orange_notification)

            }
            "Prepaid Debit card"->{
                imageView.setImageResource(R.drawable.ic_purple_notification)

            }
            "Account Overview"->{
                imageView.setImageResource(R.drawable.ic_brown_notification)

            }
        }
    }*/

   /* @BindingAdapter(value = ["setNotificationIconBlue"],requireAll = false)
    @JvmStatic
    fun setNotificationIconBlue(imageView: ImageView,value:String){
        imageView.setImageResource(R.drawable.ic_blue_notification)
    }*/

   /* @BindingAdapter(value = ["setFaqBackground","setArrow"],requireAll = false)
    @JvmStatic
    fun setFaqBackground(constraintLayout: ConstraintLayout,boolean: ObservableBoolean?,imageView: ImageView){
        boolean?.let {
            if (boolean.get()){
                constraintLayout.background = ContextCompat.getDrawable((constraintLayout.context),R.drawable.faq_selected_border)
                imageView.rotation = 180f
            }
            else{
                imageView.rotation = 0f
                constraintLayout.background = ContextCompat.getDrawable((constraintLayout.context),
                    R.drawable.edittext_bg)
            }
        }
    }*/

    @BindingAdapter(value = ["setImageDrawable"],requireAll = false)
    @JvmStatic
    fun setImageDrawable(roundedImageView: RoundedImageView, res:Int?){
        res?.let {
            roundedImageView.setImageResource(res)
        }
    }

    @BindingAdapter(value = ["set_grid_adapter","grid_items"],requireAll = false)
    @JvmStatic
    fun <T>set_grid_adapter(recyclerView: RecyclerView?, adapter: DataBindingAdapter<T>?, list: ArrayList<T>?){
        val layoutManager = GridLayoutManager(recyclerView!!.context, 2)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter != null) {
                    when (adapter.getItemViewType(position)) {
                        1 -> 1
                        0 -> 2 //number of columns of the grid
                        else -> -1
                    }
                } else {
                    -1
                }
            }
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        adapter?.submitList(list)
    }

}