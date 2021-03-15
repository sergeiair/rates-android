package com.sz.ratespal.api.authorized

import com.sz.ratespal.api.base.BaseApiInteractor
import javax.inject.Inject

class AuthorizedApiInteractor @Inject constructor(
    private val authApiService: AuthorizedApiService
): BaseApiInteractor() {
    suspend fun getHistory(limit: Int) = apiCall {
        authApiService.getHistory(limit)
    }
}