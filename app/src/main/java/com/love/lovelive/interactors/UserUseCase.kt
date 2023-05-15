package com.love.lovelive.interactors

import com.love.lovelive.retrofit.AppApiServices
import com.love.lovelive.utils.apiRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap

class UserUseCase constructor(private val networkDataSource:AppApiServices) {
    /**
     * Login
     */
    /*suspend fun login(param: LoginRequest): Flow<Resource<LoginResponse>?> = flow {
        val data = apiRequest {
            networkDataSource.login(param)
        }
        emit(data)
    }*/

  /*  suspend fun registerUser(email:String): Flow<Resource<Boolean>?> = flow {
        val data = apiRequest {
            networkDataSource.registerUser(email)
        }
        emit(data)
    }

    suspend fun verifyOtp(hashMap: HashMap<String,String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.verifyOtp(hashMap)
        }
        emit(data)
    }

    suspend fun verifyForgotOtp(hashMap: HashMap<String,String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.verifyForgotOtp(hashMap)
        }
        emit(data)
    }

    suspend fun profileSetup(requestBody: RequestBody):Flow<Resource<ProfileDataModel>?> = flow {
        val data = apiRequest {
            networkDataSource.profileSetup(requestBody)
        }
        emit(data)
    }

    suspend fun resendOtp(email:String):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.resendOtp(email)
        }
        emit(data)
    }

    suspend fun resendForgotOtp(email:String):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.resendForgotOtp(email)
        }
        emit(data)
    }

    suspend fun userLogin(loginRequestModel: LoginRequestModel?):Flow<Resource<LoginResponseModel>?> = flow {
        val dataModel = apiRequest {
            networkDataSource.userLogin(loginRequestModel)
        }
        emit(dataModel)
    }

    suspend fun userLogout():Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.userLogout()
        }
        emit(data)
    }

    suspend fun forgotPassword(email:String):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.forgotPassword(email)
        }
        emit(data)
    }

    suspend fun countriesList():Flow<Resource<CountryListModel>?> = flow {
        val data = apiRequest {
            networkDataSource.countriesList()
        }
        emit(data)
    }

    suspend fun getSubUsersList(hashMap: HashMap<String, String>):Flow<Resource<SubUserListModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getSubUsersList(hashMap)
        }
        emit(data)
    }

    suspend fun createSubProfile(requestBody: RequestBody):Flow<Resource<SubUser>?> = flow {
        val data = apiRequest {
            networkDataSource.createSubProfile(requestBody)
        }
        emit(data)
    }

    suspend fun resetPassword(hashMap: HashMap<String, String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.resetPassword(hashMap)
        }
        emit(data)
    }

    suspend fun getFeeds(hashMap: HashMap<String, String>):Flow<Resource<FeedsModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getFeeds(hashMap)
        }
        emit(data)
    }

    suspend fun createPosts(requestBody: RequestBody):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.createPosts(requestBody)
        }
        emit(data)
    }

    suspend fun updateSubProfile(requestBody: RequestBody):Flow<Resource<SubUser>?> = flow {
        val data = apiRequest {
            networkDataSource.updateSubProfile(requestBody)
        }
        emit(data)
    }

    suspend fun getSubUserPosts(hashMap: HashMap<String, String>):Flow<Resource<FeedsModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getSubUserPosts(hashMap)
        }
        emit(data)
    }

    suspend fun getParentUsers(): Flow<Resource<ParentUsersModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getParentUsers()
        }
        emit(data)
    }

    suspend fun requestUser(requestFollowModel: RequestFollowModel):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.requestUser(requestFollowModel)
        }
        emit(data)
    }

    suspend fun getFollowRequests(): Flow<Resource<FollowRequestListModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getFollowRequests()
        }
        emit(data)
    }

    suspend fun approveRequest(approveRequestModel: ApproveRequestModel): Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.approveRequest(approveRequestModel)
        }
        emit(data)
    }

    suspend fun likePost(hashMap: HashMap<String, String>):Flow<Resource<Post>?> = flow {
        val data = apiRequest {
            networkDataSource.likePost(hashMap)
        }
        emit(data)
    }

    suspend fun getPostsLikes(id:String):Flow<Resource<LikesListModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getPostsLikes(id)
        }
        emit(data)
    }

    suspend fun getSubUserProfile(hashMap: HashMap<String, String>):Flow<Resource<SubUser>?> = flow {
        val data = apiRequest {
            networkDataSource.getSubUserProfile(hashMap)
        }
        emit(data)
    }

    suspend fun getSinglePost(hashMap: HashMap<String, String>):Flow<Resource<Post>?> = flow {
        val data = apiRequest {
            networkDataSource.getSinglePost(hashMap)
        }
        emit(data)
    }

    suspend fun getPostComments(hashMap: HashMap<String, String>):Flow<Resource<CommentsListModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getPostComments(hashMap)
        }
        emit(data)
    }

    suspend fun addComment(hashMap: HashMap<String, String>):Flow<Resource<CommentsData>?> = flow {
        val data = apiRequest {
            networkDataSource.addComment(hashMap)
        }
        emit(data)
    }

    suspend fun getConnectionList(id: String):Flow<Resource<ConnectionsListModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getConnectionList(id)
        }
        emit(data)
    }

    suspend fun getFollowersList(id: String):Flow<Resource<FollowersListModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getFollowersList(id)
        }
        emit(data)
    }

    suspend fun getFollowingList(id: String):Flow<Resource<FollowingListModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getFollowingList(id)
        }
        emit(data)
    }

    suspend fun requestFollowBack(hashMap: HashMap<String, String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.requestFollowBack(hashMap)
        }
        emit(data)
    }

    suspend fun getSettings():Flow<Resource<SettingsModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getSettings()
        }
        emit(data)
    }

    suspend fun changeSettings(changeSettingModel: ChangeSettingModel):Flow<Resource<SettingsModel>?> = flow {
        val data = apiRequest {
            networkDataSource.changeSettings(changeSettingModel)
        }
        emit(data)
    }

    suspend fun deleteComment(hashMap: HashMap<String, String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.deleteComment(hashMap)
        }
        emit(data)
    }

    suspend fun likeComment(hashMap: HashMap<String, String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.likeComment(hashMap)
        }
        emit(data)
    }

    suspend fun editPost(requestBody: RequestBody):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.editPost(requestBody)
        }
        emit(data)
    }

    suspend fun addCommentReply(hashMap: HashMap<String, String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.addCommentReply(hashMap)
        }
        emit(data)
    }

    suspend fun report(hashMap: HashMap<String, String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.report(hashMap)
        }
        emit(data)
    }

    suspend fun deletePost(hashMap: HashMap<String, String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.deletePost(hashMap)
        }
        emit(data)
    }

    suspend fun deleteReplyComment(hashMap: HashMap<String, String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.deleteReplyComment(hashMap)
        }
        emit(data)
    }

    suspend fun likeReplyComment(hashMap: HashMap<String, String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.likeReplyComment(hashMap)
        }
        emit(data)
    }

    suspend fun changePassword( hashMap: HashMap<String, String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.changePassword(hashMap)
        }
        emit(data)
    }

    suspend fun checkFollowRequest(graph_parent_id:String):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.checkFollowRequest(graph_parent_id)
        }
        emit(data)
    }

    suspend fun updateParentProfile(requestBody: RequestBody):Flow<Resource<ProfileDataModel>?> = flow {
        val data = apiRequest {
            networkDataSource.updateParentProfile(requestBody)
        }
        emit(data)
    }

    suspend fun getParentProfile() : Flow<Resource<ProfileDataModel>?> = flow {
        val data = apiRequest {
            networkDataSource.getParentProfile()
        }
        emit(data)
    }

    suspend fun sendEmailOtp(email:String): Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.sendEmailOtp(email)
        }
        emit(data)
    }

    suspend fun verifyEmailOtp(hashMap: HashMap<String,String>):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.verifyEmailOtp(hashMap)
        }
        emit(data)
    }

    suspend fun updateParentEmail(email:String):Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.updateParentEmail(email)
        }
        emit(data)
    }

    suspend fun deleteSubUser(sub_user_id:String) : Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.deleteSubUser(sub_user_id)
        }
        emit(data)
    }

    suspend fun deleteAccount():Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.deleteAccount()
        }
        emit(data)
    }

    suspend fun removeConnection(subuser_id:String) : Flow<Resource<Nothing>?> = flow {
        val data = apiRequest {
            networkDataSource.removeConnection(subuser_id)
        }
        emit(data)
    }*/



}