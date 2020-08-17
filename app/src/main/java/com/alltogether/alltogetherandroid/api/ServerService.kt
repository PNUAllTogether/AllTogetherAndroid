package com.alltogether.alltogetherandroid.api

import com.alltogether.alltogetherandroid.dto.checkExistUser
import com.alltogether.alltogetherandroid.dto.userInfo
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.*

interface ServerService {
    @GET("/alltogetherspring-0.0.1-SNAPSHOT/v1/user/info")
    fun checkExistUser(@Query("id") id : Int) : Single<checkExistUser>

//    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/user/register")
//    @FormUrlEncoded
//    fun postUserInfo(@FieldMap(encoded = true) response: Map<String,String>) : Single<ResponseBody>

//    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/user/register")
//    fun postUserInfo(@Body response: String) : Single<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/user/register")
    fun postUserInfo(@Body userInfo: userInfo) : Single<ResponseBody>
}