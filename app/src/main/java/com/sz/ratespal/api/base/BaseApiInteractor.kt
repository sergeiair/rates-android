package com.sz.ratespal.api.base

import retrofit2.Response

abstract class BaseApiInteractor {

    protected suspend fun <T> apiCall(call: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val response = call()
            val body = response.body()

            when(response.isSuccessful && body != null) {
                true -> ApiResponse.success(body, response.headers().get("GoAway"))
                else -> error(" ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ApiResponse<T> {
        return ApiResponse.error("Network error: $message")
    }

}