package com.love.lovelive.retrofit


import com.love.lovelive.interactors.ApiResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService
{

   /* @FormUrlEncoded
    @POST(EndPoints.LOGIN)
    suspend fun userLogin(@FieldMap data: HashMap<String, String>): Response<ApiResponse<RegisterModel>>*/

  /*  @FormUrlEncoded
    @POST(EndPoints.REGISTER)
    suspend fun registerUser(@Field("email")email:String):Response<ApiResponse<Boolean>>

    @FormUrlEncoded
    @POST(EndPoints.VERIFY_OTP)
    suspend fun verifyOtp(@FieldMap hashMap: HashMap<String,String>):Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.VERIFY_FORGOT_OTP)
    suspend fun verifyForgotOtp(@FieldMap hashMap: HashMap<String,String>):Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.RESEND_OTP)
    suspend fun resendOtp(@Field("email")email:String):Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.RESEND_FORGOT_OTP)
    suspend fun resendForgotOtp(@Field("email")email:String):Response<ApiResponse<Nothing>>

    @POST(EndPoints.PROFILE_SETUP)
    suspend fun profileSetup(@Body requestBody: RequestBody):Response<ApiResponse<ProfileDataModel>>

    @FormUrlEncoded
    @POST(EndPoints.RESET_PASSWORD)
    suspend fun resetPassword(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Nothing>>

    @POST(EndPoints.LOGIN)
    suspend fun userLogin(@Body loginRequestModel: LoginRequestModel?):Response<ApiResponse<LoginResponseModel>>

    @GET(EndPoints.LOGOUT)
    suspend fun userLogout():Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.FORGOT_PASSWORD)
    suspend fun forgotPassword(@Field("email")email:String):Response<ApiResponse<Nothing>>

    @GET(EndPoints.COUNTRIES)
    suspend fun countriesList():Response<ApiResponse<CountryListModel>>

    @FormUrlEncoded
    @POST(EndPoints.SUB_USER_LIST)
    suspend fun getSubUsersList(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<SubUserListModel>>

    @POST(EndPoints.CREATE_SUB_PROFILE)
    suspend fun createSubProfile(@Body requestBody: RequestBody):Response<ApiResponse<SubUser>>

    @FormUrlEncoded
    @POST(EndPoints.POSTS)
    suspend fun getFeeds(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<FeedsModel>>

    @POST(EndPoints.CREATE_POSTS)
    suspend fun createPosts(@Body requestBody: RequestBody):Response<ApiResponse<Nothing>>

    @POST(EndPoints.UPDATE_SUB_PROFILE)
    suspend fun updateSubProfile(@Body requestBody: RequestBody):Response<ApiResponse<SubUser>>

    @FormUrlEncoded
    @POST(EndPoints.SUB_USER_POSTS)
    suspend fun getSubUserPosts(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<FeedsModel>>

    @GET(EndPoints.PARENT_USERS)
    suspend fun getParentUsers(): Response<ApiResponse<ParentUsersModel>>

    @POST(EndPoints.REQUEST_USER)
    suspend fun requestUser(@Body requestFollowModel: RequestFollowModel):Response<ApiResponse<Nothing>>

    @GET(EndPoints.FOLLOW_REQUEST_LIST)
    suspend fun getFollowRequests(): Response<ApiResponse<FollowRequestListModel>>

    @POST(EndPoints.APPROVE_REQUEST)
    suspend fun approveRequest(@Body approveRequestModel: ApproveRequestModel): Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.POST_LIKE)
    suspend fun likePost(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Post>>

    @GET(EndPoints.GET_POST_LIKES)
    suspend fun getPostsLikes(@Path("id") id:String):Response<ApiResponse<LikesListModel>>

    @FormUrlEncoded
    @POST(EndPoints.GET_SUB_USER_PROFILE)
    suspend fun getSubUserProfile(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<SubUser>>

    @FormUrlEncoded
    @POST(EndPoints.GET_SINGLE_POST)
    suspend fun getSinglePost(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Post>>

    @FormUrlEncoded
    @POST(EndPoints.GET_POST_COMMENTS)
    suspend fun getPostComments(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<CommentsListModel>>

    @FormUrlEncoded
    @POST(EndPoints.ADD_COMMENT)
    suspend fun addComment(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<CommentsData>>

    @GET(EndPoints.CONNECTION_LIST)
    suspend fun getConnectionList(@Path("id") id: String):Response<ApiResponse<ConnectionsListModel>>

    @GET(EndPoints.FOLLOWERS_LIST)
    suspend fun getFollowersList(@Path("id") id: String):Response<ApiResponse<FollowersListModel>>

    @GET(EndPoints.FOLLOWING_LIST)
    suspend fun getFollowingList(@Path("id") id: String):Response<ApiResponse<FollowingListModel>>

    @FormUrlEncoded
    @POST(EndPoints.FOLLOW_BACK_REQUEST)
    suspend fun requestFollowBack(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Nothing>>

    @GET(EndPoints.GET_SETTINGS)
    suspend fun getSettings():Response<ApiResponse<SettingsModel>>

    @POST(EndPoints.CHANGE_SETTINGS)
    suspend fun changeSettings(@Body changeSettingModel: ChangeSettingModel):Response<ApiResponse<SettingsModel>>

    @FormUrlEncoded
    @POST(EndPoints.DELETE_COMMENT)
    suspend fun deleteComment(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.LIKE_COMMENT)
    suspend fun likeComment(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Nothing>>

    @POST(EndPoints.EDIT_POST)
    suspend fun editPost(@Body requestBody: RequestBody):Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.ADD_COMMENT_REPLY)
    suspend fun addCommentReply(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.REPORT)
    suspend fun report(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.DELETE_POST)
    suspend fun deletePost(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.DELETE_REPLY_COMMENT)
    suspend fun deleteReplyComment(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.LIKE_REPLY_COMMENT)
    suspend fun likeReplyComment(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.CHANGE_PASSWORD)
    suspend fun changePassword(@FieldMap hashMap: HashMap<String, String>):Response<ApiResponse<Nothing>>

    @POST(EndPoints.FILE_UPLOAD)
    suspend fun uploadFile(@Body requestBody: RequestBody):Response<UploadFileModel>

    @FormUrlEncoded
    @POST(EndPoints.CHECK_FOLLOW_REQUEST)
    suspend fun checkFollowRequest(@Field("graph_parent_id") graph_parent_id:String):Response<ApiResponse<Nothing>>


    @POST(EndPoints.UPDATE_PARENT_PROFILE)
    suspend fun updateParentProfile(@Body requestBody: RequestBody):Response<ApiResponse<ProfileDataModel>>

    @GET(EndPoints.GET_PARENT_PROFILE)
    suspend fun getParentProfile() : Response<ApiResponse<ProfileDataModel>>
    @FormUrlEncoded
    @POST(EndPoints.SEND_OTP)
    suspend fun sendEmailOtp(@Field("email") email:String): Response<ApiResponse<Nothing>>
    @FormUrlEncoded
    @POST(EndPoints.VERIFY_EMAIL_OTP)
    suspend fun verifyEmailOtp(@FieldMap hashMap: HashMap<String,String>):Response<ApiResponse<Nothing>>
    @FormUrlEncoded
    @POST(EndPoints.UPDATE_PARENT_EMAIL)
    suspend fun updateParentEmail(@Field("email") email:String):Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.DELETE_SUB_USER)
    suspend fun deleteSubUser(@Field("sub_user_id") sub_user_id:String) : Response<ApiResponse<Nothing>>

    @GET(EndPoints.DELETE_ACCOUNT)
    suspend fun deleteAccount():Response<ApiResponse<Nothing>>

    @FormUrlEncoded
    @POST(EndPoints.REMOVE_CONNECTION)
    suspend fun removeConnection(@Field("subuser_id") subuser_id:String) : Response<ApiResponse<Nothing>>*/



 /*   @GET(EndPoints.SUB_USER_CONNECTIONS)
    suspend fun getSubUserConnections():
*/



}