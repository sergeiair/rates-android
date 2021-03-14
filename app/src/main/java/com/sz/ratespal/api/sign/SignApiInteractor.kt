package com.sz.ratespal.api.sign

import com.sz.ratespal.api.base.BaseApiInteractor
import com.sz.ratespal.entities.LoginRequest
import timber.log.Timber
import javax.inject.Inject

class SignApiInteractor @Inject constructor(
    private val signApiService: SignApiService
): BaseApiInteractor() {
    suspend fun login(email: String, pw: String) = apiCall {
        signApiService.login(LoginRequest(
            email, pw
        ))
    }
}