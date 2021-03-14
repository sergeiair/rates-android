package com.sz.ratespal.api.base

data class ApiResponse<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        PENDING,
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> pending(data: T? = null): ApiResponse<T> {
            return ApiResponse(Status.PENDING, data, null)
        }

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): ApiResponse<T> {
            return ApiResponse(Status.ERROR, data, message)
        }
    }
}