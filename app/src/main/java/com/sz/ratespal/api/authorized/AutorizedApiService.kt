package com.sz.ratespal.api.authorized

import com.sz.ratespal.entities.RatesResponseWrapper
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthorizedApiService {

    @GET("rates/history")
    suspend fun getHistory(@Query("limit") limit: Int)
        : Response<RatesResponseWrapper>

}