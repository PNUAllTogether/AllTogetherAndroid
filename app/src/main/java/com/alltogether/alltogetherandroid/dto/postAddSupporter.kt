package com.alltogether.alltogetherandroid.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class postAddSupporter (
    @SerializedName("childId")
    @Expose
    val childId: Int,
    @SerializedName("supporterId")
    @Expose
    val supporterId: Int
)