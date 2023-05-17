package com.love.lovelive.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import timber.log.Timber

abstract class BaseFragment<T: ViewDataBinding>  : Fragment(){


    private var mViewDataBinding: T? = null
    private var mRootView: View? = null


    @get:LayoutRes
    protected abstract val layoutResourceId: Int


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        mRootView = mViewDataBinding?.root.also {
            Timber.e(javaClass.simpleName)
        }
        return mRootView
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mRootView = null
        System.gc()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding!!.executePendingBindings()

    }

    open fun getViewDataBinding(): T {
        return mViewDataBinding!!
    }

    fun moveNext(resId:Int){
        try {
            findNavController().navigate(resId)
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    /**
     * move to next fragment with arguments
     */
    fun moveNextArgs(resId: Int, args: Bundle) {
        try {
            findNavController().navigate(resId, args)
        } catch (e: Exception) {
            Timber.e(e.toString())
        }
    }
}