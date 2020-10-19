package com.example.hilt_with_testing.db

import androidx.room.*
import com.example.hilt_with_testing.modal.UserResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insert(userResponse: UserResponse)

    @Update
    suspend fun update(userResponse: UserResponse)

    @Delete
    suspend fun delete(userResponse: UserResponse)

    @Query("SELECT * FROM USERRESPONSE")
    fun getUsers() : Flow<List<UserResponse>>

}