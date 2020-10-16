package com.example.hilt_with_testing.utility

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RegistrationUtilTest{

    @Test
     fun `empty username return false`(){
         val result = RegistrationUtil.validationRegistrationInput(
             "",
             "123",
             "123"
         )
        assertThat(result).isFalse()
     }

    @Test
    fun `valid username and correctly repeated password returns true`(){
        val result = RegistrationUtil.validationRegistrationInput(
            "Philip",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `username is already exist return false`(){
        val result = RegistrationUtil.validationRegistrationInput(
            "Carl",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password is empty return false`(){
        val result = RegistrationUtil.validationRegistrationInput(
            "Philip",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `incorrectly confirmed password return false`(){
        val result = RegistrationUtil.validationRegistrationInput(
            "Philip",
            "123",
            "321"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `password contain less than 2 digits return false`(){
        val result = RegistrationUtil.validationRegistrationInput(
            "Philip",
            "adas1",
            "adas1"
        )
        assertThat(result).isFalse()
    }
}