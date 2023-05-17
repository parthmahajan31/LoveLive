package com.love.lovelive.retrofit

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        private var retrofit: Retrofit? = null

        private fun providersLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor { message ->
                Log.e(
                    "API_RESPONSE", "Log: $message"
                )
            }
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }


        fun getClient(): Retrofit? {
            val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
                .callTimeout(200, TimeUnit.MINUTES)
                .readTimeout(200, TimeUnit.SECONDS)
                .connectTimeout(200, TimeUnit.SECONDS)
                .writeTimeout(200, TimeUnit.SECONDS)
                .addInterceptor(AuthenticationInterceptor())
                .addInterceptor(providersLoggingInterceptor())
//                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            retrofit = Retrofit.Builder()
                .baseUrl(EndPoints.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
            return retrofit
        }
    }
}