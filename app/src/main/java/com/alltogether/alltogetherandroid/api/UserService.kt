package com.alltogether.alltogetherandroid.api

import com.alltogether.alltogetherandroid.dto.naverOAuthResponse
import com.alltogether.alltogetherandroid.dto.verifyToken
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/v1/nid/me")
    fun getUserInfo(@Header("Authorization") accessToken:String) : Single<naverOAuthResponse>

    @GET("/v1/nid/verify")
    fun verifyAccessToken(@Header("Authorization") accessToken:String) : Single<verifyToken>
}