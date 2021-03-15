package com.sz.ratespal.api.interceptors

import android.content.SharedPreferences
import com.sz.ratespal.App
import com.sz.ratespal.utils.Print
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AddHeaderInterceptor: Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        App.token?.let {
            builder.addHeader("GoAway", it)
        }

        return chain.proceed(builder.build())
    }

}
