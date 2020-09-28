package com.example.hilt_with_testing.modal

import com.google.gson.annotations.SerializedName


data class UserResponse (
    @SerializedName("id") val id : Int,
    @SerializedName("userId") val userId: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)