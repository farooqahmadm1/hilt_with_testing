package com.example.hilt_with_testing.modal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class UserResponse (
    @PrimaryKey
    @SerializedName("id") val id : Int,
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("website") val website: String
)