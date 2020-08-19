package com.alltogether.alltogetherandroid.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class postInt (
    @SerializedName("id")
    @Expose
    val id: Int
)