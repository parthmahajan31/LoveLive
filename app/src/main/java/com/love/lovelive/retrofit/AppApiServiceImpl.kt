package com.love.lovelive.retrofit

import com.love.lovelive.interactors.ApiResponse
import com.love.lovelive.models.request.LoginSignupRequest
import com.love.lovelive.models.response.ProfileModel
import okhttp3.RequestBody
import retrofit2.Response

class AppApiServiceImpl

constructor(private val apiServices: ApiService) : AppApiServices {


    //    override suspend fun userRegister(hashMap:HashMap<String, RequestBody>,profile_image: MultipartBody.Part):Response<ApiResponse<RegisterModel>>{
//        return apiService.userRegister(hashMap,profile_image)
//    }
    override suspend fun loginSignup(loginSignupRequest: LoginSignupRequest): Response<ApiResponse<ProfileModel>> {
        return apiServices.loginSignup(loginSignupRequest)
    }

    override suspend fun getProfile(): Response<ApiResponse<ProfileModel>> {
        return apiServices.getProfile()
    }


}