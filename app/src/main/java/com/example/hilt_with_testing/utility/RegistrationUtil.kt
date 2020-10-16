package com.example.hilt_with_testing.utility

object RegistrationUtil {

    private val existingUsers = listOf<String>("Peter","Carl")

    /**
     * the input is not valid if ....
     * ... the username/password is empty
     * ... the username is already taken
     * ... the confirmed password is not the same as real password
     * ... the password contain less than 2 digits
     */

    fun validationRegistrationInput(
        username: String,
        password: String,
        confirmPassword : String
    ): Boolean{
        if (username.isEmpty() || password.isEmpty()){
            return  false
        }
        if (username in existingUsers){
            return false
        }
        if (password != confirmPassword){
            return false
        }
        if (password.count(){it.isDigit()} < 2){
            return false
        }
        return true
    }
}