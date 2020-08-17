package com.alltogether.alltogetherandroid.repository

import com.alltogether.alltogetherandroid.dto.checkExistUser
import com.alltogether.alltogetherandroid.dto.userInfo
import io.reactivex.Single
import okhttp3.ResponseBody

interface ServerRepository {
    fun checkExistUser(id : Int) : Single<checkExistUser>
//    fun postUserInfo(response : Map<String,String>) : Single<ResponseBody>
    fun postUserInfo(response : userInfo) : Single<ResponseBody>
}