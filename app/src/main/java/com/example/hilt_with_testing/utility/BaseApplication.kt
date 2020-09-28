package com.example.hilt_with_testing.utility

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }

}