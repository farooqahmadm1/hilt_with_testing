package com.example.hilt_with_testing.hilt

import android.app.Application
import androidx.room.Room
import com.example.hilt_with_testing.db.HiltTestingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class DbModule {
    
    @Provides
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, HiltTestingDatabase::class.java, "HiltDatabase")
            .fallbackToDestructiveMigration()
            .build()
}