package com.alltogether.alltogetherandroid.api

import com.alltogether.alltogetherandroid.dto.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface ServerService {
    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/user/check")
    fun checkExistUser(@Body id : postInt) : Single<checkExistUser>

//    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/user/register")
//    @FormUrlEncoded
//    fun postUserInfo(@FieldMap(encoded = true) response: Map<String,String>) : Single<ResponseBody>

//    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/user/register")
//    fun postUserInfo(@Body response: String) : Single<ResponseBody>

//    @Headers("Content-Type: application/json")
//    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/user/signup")
//    fun postUserInfo(@Body userInfo: userInfo) : Single<ResponseBody>
    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/user/signup")
    fun postParentInfo(@Body parentInfo: parentInfo) : Single<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/user/signup")
    fun postSupporterInfo(@Body supporterInfo: supporterInfo) : Single<ResponseBody>
}