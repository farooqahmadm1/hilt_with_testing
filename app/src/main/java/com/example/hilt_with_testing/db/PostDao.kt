package com.example.hilt_with_testing.db

import androidx.room.*
import com.example.hilt_with_testing.modal.Post
import kotlinx.coroutines.flow.Flow
import javax.crypto.spec.PSource

@Dao
interface PostDao {

    @Insert
    suspend fun insert(post: Post)

    @Update
    suspend fun update(post: Post)

    @Delete
    suspend fun delete(post: Post)

    @Query("SELECT * FROM POST")
    fun getPosts() : Flow<List<Post>>
}