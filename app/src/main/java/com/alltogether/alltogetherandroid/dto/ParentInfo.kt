package com.alltogether.alltogetherandroid.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ParentInfo(@SerializedName("email") @Expose val email: String,
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("username") @Expose val name: String,
    @SerializedName("image") @Expose val profile_image: String,
    @SerializedName("usertype") @Expose val usertype: userType,
    @SerializedName("childType") @Expose val childType: String,
    @SerializedName("childAge") @Expose val childAge: Int,
    @SerializedName("childCharacter") @Expose val childCharacter: String,
    @SerializedName("childGoal") @Expose val childGoal: String,
    @SerializedName("childName") @Expose val childName: String)