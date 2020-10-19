package com.example.hilt_with_testing.network

import com.example.hilt_with_testing.modal.UserResponse
import kotlinx.coroutines.flow.Flow

import retrofit2.http.GET
import retrofit2.http.Path

interface RestApiServices {
    @GET("posts/{id}")
    suspend fun getUser(@Path("id") id: Int): UserResponse

    @GET("posts")
    suspend fun getPostList(): List<UserResponse>

    @GET("posts")
    fun getPostLists(): Flow<List<UserResponse>>

    @GET("users")
    fun getUsers() : Flow<List<UserResponse>>
}