package com.alltogether.alltogetherandroid.api

import com.alltogether.alltogetherandroid.dto.*
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
fun postParentInfo(@Body parentInfo: ParentInfo): Single<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/user/signup")
    fun postSupporterInfo(@Body supporterInfo: SupporterInfo): Single<ResponseBody>

    @GET("/alltogetherspring-0.0.1-SNAPSHOT/v1/search/filter")
    fun filterSupporter(
            @Query("region") region : String,
            @Query("major") major : String
    ) : Single<supporterSearchResult>

    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/child/info")
    fun getChildInfo(@Body id : postInt) : Single<getChildInfoResult>

    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/child/add-supporter")
    fun addSupporter(@Body id : postAddSupporter) : Single<addSupporterResult>

    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/child/members")
    fun currentSupporter(@Body id : postChild) : Single<supporterSearchResult>

    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/check-list")
    fun getAllCheckList(@Body id : postChild) : Single<getCheckListResult>

    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/check-list/insert")
    fun addCheckList(@Body id : postCheckList) : Single<getCheckListResult>

    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/check-list/done")
    fun addDone(@Body id : postDone) : Single<getCheckListResult>

    @Headers("Content-Type: application/json")
    @POST("/alltogetherspring-0.0.1-SNAPSHOT/v1/check-list/delete")
    fun delete(@Body id : postDelete) : Single<getCheckListResult>
}