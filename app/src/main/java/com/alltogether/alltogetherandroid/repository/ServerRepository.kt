package com.alltogether.alltogetherandroid.repository

import com.alltogether.alltogetherandroid.dto.checkExistUser
import com.alltogether.alltogetherandroid.dto.parentInfo
import com.alltogether.alltogetherandroid.dto.supporterInfo
import io.reactivex.Single
import okhttp3.ResponseBody

interface ServerRepository {
    fun checkExistUser(id : Int) : Single<checkExistUser>
//    fun postUserInfo(response : Map<String,String>) : Single<ResponseBody>
//    fun postUserInfo(response : userInfo) : Single<ResponseBody>
    fun postParentInfo(response : parentInfo) : Single<ResponseBody>
    fun postSupporterInfo(response : supporterInfo) : Single<ResponseBody>
}