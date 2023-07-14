package com.love.lovelive.models.request

data class LoginSignupRequest(
    val countryCode: Int? = null,
    val deviceToken: String? = null,
    val deviceType: String,
    val isProfileCompleted: Boolean,
    val loginType: String,
    val phoneNumber: String? = null,
    val socialId: String? =null,
    val name: String? = null,
    val email: String? = null,
    val profilePic: String? = null,
)