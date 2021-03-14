package com.sz.ratespal.api.sign

import com.sz.ratespal.entities.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignApiService {

    @POST("users/login")
    suspend fun login(@Body body: LoginRequest) : Response<Unit>

}