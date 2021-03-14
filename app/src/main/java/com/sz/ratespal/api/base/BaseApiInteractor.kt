package com.sz.ratespal.api.base

import retrofit2.Response
import timber.log.Timber

abstract class BaseApiInteractor {

    protected suspend fun <T> apiCall(call: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val response = call()
            Timber.d(response.toString())
            val body = response.body()

            when(response.isSuccessful && body != null) {
                true -> ApiResponse.success(body)
                else -> error(" ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): ApiResponse<T> {
        Timber.d(message)
        return ApiResponse.error("Network error: $message")
    }

}