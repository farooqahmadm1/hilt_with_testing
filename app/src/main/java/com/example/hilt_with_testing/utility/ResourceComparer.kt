package com.example.hilt_with_testing.utility

import android.content.Context

class ResourceComparer {

    fun isEqual(context: Context, resId : Int, string: String) : Boolean{
        return context.getString(resId) == string
    }
}