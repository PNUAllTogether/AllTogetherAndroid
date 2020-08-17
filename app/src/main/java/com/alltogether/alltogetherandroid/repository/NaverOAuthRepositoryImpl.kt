package com.alltogether.alltogetherandroid.repository

import com.alltogether.alltogetherandroid.api.UserService
import com.alltogether.alltogetherandroid.dto.naverOAuthResponse
import io.reactivex.Single

class NaverOAuthRepositoryImpl constructor(private val userService: UserService) : NaverOAuthRepository {
    override fun getUserInfo(accessToken: String): Single<naverOAuthResponse> {
        return userService.getUserInfo("Bearer $accessToken")
    }
}