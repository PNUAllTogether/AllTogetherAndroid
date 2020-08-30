package com.alltogether.alltogetherandroid.dto

data class Check(
    val childId: Int,
    val content: String,
    val isDone: Boolean,
    val itemId: Int
)