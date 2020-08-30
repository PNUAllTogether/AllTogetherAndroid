package com.alltogether.alltogetherandroid.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class postChild (
    @SerializedName("childId")
    @Expose
    val childId: Int
)