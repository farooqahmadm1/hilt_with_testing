package com.example.hilt_with_testing.modal

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Post (
    @PrimaryKey
    @SerializedName("id") val id : Int,
    @SerializedName("userId") var userId : Int,
    @SerializedName("title") val title : String,
    @SerializedName("body") val body : String
)