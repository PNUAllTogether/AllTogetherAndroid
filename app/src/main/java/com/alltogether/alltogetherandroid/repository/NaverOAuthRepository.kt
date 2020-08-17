package com.alltogether.alltogetherandroid.repository

import com.alltogether.alltogetherandroid.dto.naverOAuthResponse
import io.reactivex.Single

interface NaverOAuthRepository {
    fun getUserInfo(accessToken : String) : Single<naverOAuthResponse>
}