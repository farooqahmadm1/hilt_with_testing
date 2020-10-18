package com.example.hilt_with_testing.utility

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.hilt_with_testing.R
import com.google.common.truth.Truth.assertThat
import org.junit.Before

import org.junit.Test

class ResourceComparerTest {

    private lateinit var resourceComparer : ResourceComparer
    private lateinit var context: Context

    @Before
    fun setUp() {
        resourceComparer = ResourceComparer()
        context = ApplicationProvider.getApplicationContext<Context>()
    }

    @Test
    fun stringResourceIsSameAsString_returnTrue(){
        val result = resourceComparer.isEqual(context,R.string.app_name,"hilt_with_testing")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceIsDifferentAsString_returnFalse(){
        val result = resourceComparer.isEqual(context,R.string.app_name,"hilt_")
        assertThat(result).isFalse()
    }
}