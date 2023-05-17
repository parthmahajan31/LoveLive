package com.love.lovelive.retrofit

import LocalStorage
import com.love.lovelive.delegate.MyApplication
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Manjinder Singh on 05,January,2021
 */

class AuthenticationInterceptor :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {
        val token: String? = LocalStorage.getToken(MyApplication.application)
        Timber.e("Bearer $token")

//        if (DashboardFragment.isSendToken.get()) {
        val newRequest: Request = it.newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .addHeader("Time-Zone", currentDeviceZone())
            .build()
//        }else{
//            newRequest = it.newBuilder()
//                .build()
//        }
        chain.proceed(newRequest)
    }

    private fun currentDeviceZone(): String {
        val sdf = SimpleDateFormat("ZZZZZ")
        val currentDate = sdf.format(Date())
        println(" C DATE is  $currentDate")
        return currentDate
    }
}