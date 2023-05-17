package com.love.lovelive.viewmodel

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.love.lovelive.retrofit.ApiClient
import com.love.lovelive.retrofit.ApiService
import com.love.lovelive.utils.alert
import com.love.lovelive.utils.dismissProgress
import com.love.lovelive.utils.isConnected
import com.love.lovelive.utils.showProgress
import okhttp3.RequestBody

class ChatApiVm :ViewModel() {

    val api = ApiClient.getClient()?.create(ApiService::class.java)

  /*  suspend fun uploadFile(
        activity:Activity,
        requestBody: RequestBody
    ):LiveData<UploadFileModel>{
        val fileUploadMutableModel = MutableLiveData<UploadFileModel>()
        if (isConnected()){
            showProgress(activity)
            val response = api?.uploadFile(requestBody)
            if (response?.isSuccessful!!){
                dismissProgress()
                fileUploadMutableModel.postValue(response.body())
            }
            else{
                dismissProgress()
                response.message().alert(activity)
            }
        }
        return fileUploadMutableModel
    }*/

}