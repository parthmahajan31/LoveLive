package com.love.lovelive.retrofit


import com.love.lovelive.interactors.ApiResponse
import com.love.lovelive.models.request.LoginSignupRequest
import com.love.lovelive.models.response.ProfileModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService
{

   /* @FormUrlEncoded
    @POST(EndPoints.LOGIN)
    suspend fun userLogin(@FieldMap data: HashMap<String, String>): Response<ApiResponse<RegisterModel>>*/


    @POST(EndPoints.LOGIN_SIGNUP)
    suspend fun loginSignup(@Body loginSignupRequest: LoginSignupRequest):Response<ApiResponse<ProfileModel>>

    @GET(EndPoints.LOGIN_SIGNUP)
    suspend fun getProfile():Response<ApiResponse<ProfileModel>>



}