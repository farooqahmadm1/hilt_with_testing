package com.example.hilt_with_testing.utility

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class HomeWorkTest {

    @Test
    fun `braces place correct return true`(){
        val result = Homework.checkBraces("(a+b)")
        assertThat(result).isTrue()
    }

    @Test
    fun `braces place not correct return false`(){
        val result = Homework.checkBraces("(a+b))")
        assertThat(result).isFalse()
    }

    @Test
    fun `zero as input return 1`(){
        val result = Homework.fib(0)
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `1 as input return 1`(){
        val result = Homework.fib(1)
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `test result of fab(n) number`(){
        val result = Homework.fib(3)
        assertThat(result).isEqualTo(6)
    }
}