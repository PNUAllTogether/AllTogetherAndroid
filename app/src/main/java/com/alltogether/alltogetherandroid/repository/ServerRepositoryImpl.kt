package com.alltogether.alltogetherandroid.repository

import com.alltogether.alltogetherandroid.api.ServerService
import com.alltogether.alltogetherandroid.dto.checkExistUser
import com.alltogether.alltogetherandroid.dto.userInfo
import io.reactivex.Single
import okhttp3.ResponseBody

class ServerRepositoryImpl constructor(private val serverService: ServerService) : ServerRepository {
    override fun checkExistUser(id: Int): Single<checkExistUser> {
        return serverService.checkExistUser(id)
    }

//    override fun postUserInfo(response: Map<String, String>): Single<ResponseBody> {
//        return serverService.postUserInfo(response)
//    }
    override fun postUserInfo(response: userInfo): Single<ResponseBody> {
        return serverService.postUserInfo(response)
    }
}