package com.love.lovelive.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.love.lovelive.R
import com.love.lovelive.delegate.MyApplication


class CommonAlertDialog(
    mContext: Context,
    private val message: String,
    private val labelPositive: String,
    private val labelNegative: String,
    private val onButtonClickListener: OnButtonClickListener,
    private val title: String =  MyApplication.application.applicationContext?.getString(R.string.app_name)
        .toString()
) : Dialog(mContext, R.style.TransparentDilaog) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_layout)
        try {
            setCancelable(false)
            val tvMessage = findViewById<View>(R.id.tvMessage) as TextView
            val tvPositive = findViewById<View>(R.id.tvPositive) as TextView
            val tvNegative = findViewById<View>(R.id.tvNegative) as TextView
            val line = findViewById<View>(R.id.line) as View
            tvPositive.text = labelPositive
            tvMessage.text = message
            tvPositive.setOnClickListener {
                dismiss()
                onButtonClickListener.onPositiveButtonClicked()

            }

            if (labelNegative != "" && labelPositive != "") {
                tvNegative.visibility = View.VISIBLE
                line.visibility = View.VISIBLE
                tvNegative.text = labelNegative
                tvNegative.setOnClickListener {
                    onButtonClickListener.onNegativeButtonClicked()
                    dismiss()
                }
            } else if (labelPositive.isNullOrEmpty()) {
                line.visibility = View.GONE
                tvPositive.visibility = View.GONE
                tvNegative.visibility = View.VISIBLE
                tvNegative.text = labelNegative
                tvNegative.setOnClickListener {
                    onButtonClickListener.onNegativeButtonClicked()
                    dismiss()
                }
            } else {

                line.visibility = View.GONE
                tvNegative.visibility = View.GONE

            }

        } catch (e: Exception) {

        }
    }

    interface OnButtonClickListener {
        fun onPositiveButtonClicked()
        fun onNegativeButtonClicked()
    }
}




