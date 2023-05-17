package com.love.lovelive.utils

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class AuthenticationInterceptorForSocket : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let { request ->
        var newRequest: Request?

        newRequest = request.newBuilder()
            .addHeader("Time-Zone", currentDeviceZone())
            .build()

        Timber.e(Gson().toJson(newRequest.headers))
        newRequest.let { chain.proceed(it) }
    }

    private fun currentDeviceZone(): String {
        val sdf = SimpleDateFormat("ZZZZZ")
        val currentDate = sdf.format(Date())
        println(" C DATE is  $currentDate")
        return currentDate
    }

}