package com.alltogether.alltogetherandroid.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class filterBody(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("image")
    @Expose
    val image: String?,
    @SerializedName("major")
    @Expose
    val major: String,
    @SerializedName("experience")
    @Expose
    val experience: String,
    @SerializedName("introduce")
    @Expose
    val introduce: String,
    @SerializedName("cost")
    @Expose
    val cost: String,
    @SerializedName("speciality")
    @Expose
    val speciality: String,
    @SerializedName("gender")
    @Expose
    val gender: String,
    @SerializedName("age")
    @Expose
    val age: String,
    @SerializedName("officeTime")
    @Expose
    val officeTime: String,
    @SerializedName("department")
    @Expose
    val department: String,
    @SerializedName("region")
    @Expose
    val region: String
)