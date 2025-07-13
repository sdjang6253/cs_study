package com.example.demo.dto

data class SignupRequest(
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var nickname: String = ""
)

data class LoginRequest(
    var email: String = "",
    var password: String = ""
)

data class UserSession(
    val id: Long,
    val email: String,
    val nickname: String
)
