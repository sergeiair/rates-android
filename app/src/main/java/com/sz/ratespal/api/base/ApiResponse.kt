package com.sz.ratespal.api.base

data class ApiResponse<out T>(val status: Status, val data: T?, val payload: String?) {

    enum class Status {
        PENDING,
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> pending(data: T? = null): ApiResponse<T> {
            return ApiResponse(Status.PENDING, data, null)
        }

        fun <T> success(data: T, token: String?): ApiResponse<T> {
            return ApiResponse(Status.SUCCESS, data, token)
        }

        fun <T> error(message: String, data: T? = null): ApiResponse<T> {
            return ApiResponse(Status.ERROR, data, message)
        }
    }
}