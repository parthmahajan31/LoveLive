package com.love.lovelive.models.response


data class ProfileModel(
    val __v: Int,
    val _id: String,
    val accessToken: String,
    val createdAt: String,
    val deviceToken: String,
    val deviceType: String,
    val email: String,
    val interests: List<Any>,
    val isDeleted: Boolean,
    val isProfileCompleted: Boolean,
    val loginType: String,
    val name: String,
    val otpVerified: Boolean,
    val profilePic: String,
    val refreshToken: String,
    val role: String,
    val socialId: String,
    val status: Boolean,
    val updatedAt: String
)

class Meta