package com.love.lovelive.delegate

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.google.gson.Gson
import com.love.lovelive.retrofit.EndPoints
import dagger.hilt.android.HiltAndroidApp
import io.socket.client.IO
import io.socket.client.Socket
import timber.log.Timber

/**
 * Created by Parth on 23 Apr 2023
 */

@HiltAndroidApp
class MyApplication : Application() {

    private var mSocket: Socket? = null

    init {
        try {
           /* val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthenticationInterceptorForSocket()).build()
            IO.setDefaultOkHttpWebSocketFactory(okHttpClient)
            IO.setDefaultOkHttpCallFactory(okHttpClient)

            val opts = IO.Options()
            opts.callFactory = okHttpClient
            opts.webSocketFactory = okHttpClient*/

            mSocket = IO.socket(EndPoints.SOCKET_URL)
            Timber.e("id " + mSocket?.id())
            Log.e("socket", "id $mSocket")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("socket", "failed")
        }
    }

    companion object{
        lateinit var application: Context
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        Places.initialize(this, getString(R.string.api_key), Locale.US)
        mSocket?.connect()
        initTimber()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    fun getSocket(): Socket? {
        return mSocket
    }



}