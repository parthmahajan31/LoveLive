package com.love.lovelive.interactors


sealed class Resource<T>(
    val data: T? = null,
    val msg: String? = null,
    val success: Status?,
    val error:String? = null,
){
    class Success<T>(data: T?, msg: String?, status: Status?) :
        Resource<T>(data = data, msg = msg, success = status)

    class Error<T>(message: String, status: Status, error:String?,data: T?) : Resource<T>(
        msg = message,
        success = status,
        error = error,
        data = data
    )

    class NetworkError<T>(message: String, status: Status, error: String?,data: T?) : Resource<T>(
        msg = message,
        success = status,
        error = error,
        data = data
    )
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    UNAUTHROZIED
}