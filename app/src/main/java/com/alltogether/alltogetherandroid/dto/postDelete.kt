package com.alltogether.alltogetherandroid.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class postDelete (
    @SerializedName("childId")
    @Expose
    val childId: Int,
    @SerializedName("itemId")
    @Expose
    val itemId: Int
)