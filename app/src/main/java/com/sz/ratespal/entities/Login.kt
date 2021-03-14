package com.sz.ratespal.entities

data class LoginRequest(
    val email: String,
    val pw: String
)

data class LoginResponse(
    val email: String,
    val name: String,
)

data class LoginResponseData(
    val data: LoginResponse,
    val message: String,
)

data class LoginResponseWrapper(
    val data: LoginResponseData,
    val status: Int
)