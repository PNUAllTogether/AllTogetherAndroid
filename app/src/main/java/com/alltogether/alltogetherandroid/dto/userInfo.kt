package com.alltogether.alltogetherandroid.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class userInfo(
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("profile_image")
    @Expose
    val profile_image: String,
    @SerializedName("usertype")
    @Expose
    val usertype: userType
)