package com.love.lovelive.interactors

data class ApiResponse<T>(
    val success: Boolean,
    val status: Int,
    val message: String,
    val data: T,
    val error:String,
)

data class ErrorResponse(
    var message:String?,
    var success: Status?
)
