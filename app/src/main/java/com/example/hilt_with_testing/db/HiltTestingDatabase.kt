package com.example.hilt_with_testing.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hilt_with_testing.modal.Post
import com.example.hilt_with_testing.modal.UserResponse

@Database(
    entities = [
        UserResponse::class,
        Post::class
    ],
    version = 1
)
abstract class HiltTestingDatabase : RoomDatabase() {
    abstract fun UserDao(): UserDao
    abstract fun postDao(): PostDao
}