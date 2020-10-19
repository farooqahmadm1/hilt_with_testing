package com.example.hilt_with_testing.modal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class UserResponse (
    @PrimaryKey
    @SerializedName("id") val id : Int,
    @SerializedName("userId") val userId: String,
    @SerializedName("title") var title: String,
    @SerializedName("body") val body: String
)