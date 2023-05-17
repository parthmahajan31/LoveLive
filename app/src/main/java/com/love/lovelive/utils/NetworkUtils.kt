package com.love.lovelive.utils

import LocalStorage
import android.app.AlertDialog
import android.content.Intent
import android.webkit.MimeTypeMap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.love.lovelive.ui.activities.MainActivity
import com.love.lovelive.R
import com.love.lovelive.interactors.ApiResponse
import com.love.lovelive.interactors.ErrorResponse
import com.love.lovelive.interactors.Resource
import com.love.lovelive.interactors.Status
import com.love.lovelive.retrofit.EndPoints
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber
import java.io.File


suspend fun <T : Any> apiRequest(
    call: suspend () -> Response<ApiResponse<T>>
): Resource<T>? {

    return withContext(Dispatchers.IO) {
        createResource(call)
    }
}

suspend fun <T : Any> createResource(call: suspend () -> Response<ApiResponse<T>>): Resource<T>? {

    var resource: Resource<T>? = null

    try {
        if (isConnected()) {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    resource = when (response.body()?.success) {
                        true -> {
                            Resource.Success(it.data, it.message, Status.SUCCESS)
                        }
                        else -> {
                            Resource.Error(message = it.message.toString(), status = Status.ERROR,error = it.error,it.data)
                        }
                    }
                }
            } else {
                val error = response.errorBody()?.string()
                Timber.e("ErrorBody=>${error}")
                val message = StringBuilder()
                val errorMsg = StringBuilder()
                error?.let {
                    resource = try {
                        if (JSONObject(it).getString("message") != "Validation Error."){
                            message.append(JSONObject(it).getString("message"))
                        }
                       else{
                           try {
                               message.append(JSONObject(it).getJSONObject("data").getJSONArray("email").get(0))
                           }catch (e:JSONException){
                               try {
                                   message.append(JSONObject(it).getJSONObject("data").getJSONArray("title").get(0))
                               }catch (e:JSONException){
                                   message.append(JSONObject(it).getJSONObject("data").getJSONArray("current_password").get(0))
                               }
                           }

                        }

                        if (response.code() == 401){
                            Resource.Error(
                                message = message.toString(),
                                status = Status.UNAUTHROZIED,
                                error = errorMsg.toString(),
                                data = response.body()?.data)
                        }
                        else{
                            Resource.Error(message = message.toString(), status = Status.ERROR,error = errorMsg.toString(), data = response.body()?.data)
                        }
                    } catch (e: JSONException) {
                        message.append(error)
                        if (response.code() == 401) {
                            Resource.Error(
                                message = message.toString(),
                                status = Status.UNAUTHROZIED,
                                error = errorMsg.toString(),
                                data = response.body()?.data
                            )
                        } else
                            Resource.Error(response.message(), Status.ERROR,errorMsg.toString(),data = response.body()?.data)
                    }
                }
            }
        } else {
            resource = Resource.Error("Please connect to Internet", Status.ERROR,"",data = null)
        }
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        resource = Resource.NetworkError(throwable.message.toString(), Status.ERROR,"",data = null)
    }
    return resource

}

fun ErrorResponse?.checkErrorResponse(context: FragmentActivity) {
    this?.let { error ->
        Timber.e("Error=>${error.success},${Status.UNAUTHROZIED}")

         if (error.success == Status.UNAUTHROZIED) {
             try {
                 context.showAlert(
                     "Session",
                     "Session expired,please login again",
                     "Yes",
                     "",
                     {
                         context.logout()
                     },
                     {

                     }
                 )
             } catch (e: Exception) {

             }
         } else {
             error.message.toString().alert(context)
         }
    }
}

fun FragmentActivity.logout() {
    LocalStorage.clearAllData(EndPoints.LOVE_LIVE, this)
    startActivity(Intent(this, MainActivity::class.java))
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    finishAffinity()
}


fun FragmentActivity.showAlert(
    title: String,
    message: String,
    positiveButtonTitle: String,
    negativeButtonTitle: String,
    positiveClick: () -> Unit,
    negativeClick: () -> Unit
) {
    val builder = AlertDialog.Builder(this)
    //set title for alert dialog
    builder.setTitle(title)
    //set message for alert dialog
    builder.setMessage(message)

    //performing positive action
    builder.setPositiveButton(positiveButtonTitle) { dialogInterface, _ ->
        dialogInterface.dismiss()
        positiveClick.invoke()

    }
    //performing negative action
    if (negativeButtonTitle.isNotEmpty()) {
        builder.setNegativeButton(negativeButtonTitle) { dialogInterface, _ ->
            dialogInterface.dismiss()
            negativeClick.invoke()
        }
    }
    // Create the AlertDialog
    val alertDialog: AlertDialog = builder.create()
    // Set other dialog properties
    alertDialog.setCancelable(false)
    alertDialog.show()
}

fun createMultiPart(path: String?, key: String): MultipartBody.Part? {
    return if (path != null) {
        val file = File(path)
        val body = file.asRequestBody(getMimeType(path).toString().toMediaTypeOrNull())
        MultipartBody.Part.createFormData(key, file.name, body)
    } else {
        /* val body = "".toRequestBody(getMimeType(path).toString().toMediaTypeOrNull())
         MultipartBody.Part.createFormData(key, "", body)*/
        null
    }
}


fun getMimeType(url: String?): String? {
    var type: String? = null
    val extension = MimeTypeMap.getFileExtensionFromUrl(url)
    if (extension != null) {
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }
    return type
}

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context).load(url).placeholder(R.drawable.rectangle).into(view)
    }
}

/*fun multiImagePart(list: ArrayList<String>, builder: MultipartBody.Builder, key: String) {

    for (i in 0 until list.size) {
        if (!list[i].isNullOrEmpty() && !list[i].contains("http")) {
            val file = File(list[i])
            val body = file.asRequestBody(getMimeType(list[i]).toString().toMediaTypeOrNull())
            builder.addFormDataPart("$key[$i]", file.name, body)
//            builder.addFormDataPart("$key[$i][name]", file.name)
        }
    }
}*/

fun multiImagePart(list: ArrayList<String>, builder: MultipartBody.Builder, key: String) {
    var imageArray = ArrayList<RequestBody>()
    for (i in 0 until list.size) {
        if (!list[i].isNullOrEmpty() && !list[i].contains("http")) {
            val file = File(list[i])
            val body = file.asRequestBody(getMimeType(list[i]).toString().toMediaTypeOrNull())
            imageArray.add(body)

        }
    }
    builder.addFormDataPart(key,Gson().toJson(imageArray))
}

fun toRequestBody(value: String?): RequestBody {
    return RequestBody.create("text/plain".toMediaTypeOrNull(), value.toString())
}