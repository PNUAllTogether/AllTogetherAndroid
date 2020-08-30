package com.alltogether.alltogetherandroid.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class postCheckList (
    @SerializedName("childId")
    @Expose
    val childId: Int,
    @SerializedName("date")
    @Expose
    val date: String,
    @SerializedName("content")
    @Expose
    val content: String
)