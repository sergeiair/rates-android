package com.sz.ratespal.api.interceptors

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sz.ratespal.App
import com.sz.ratespal.activities.main.Navigator
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.IOException


class ErrorsHandlingInterceptor : Interceptor {

    private val currentActivity: AppCompatActivity?
        get() = App.currentActivity?.get()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        val responseBody: ResponseBody? = originalResponse.body()
        val contentType = responseBody?.contentType()

        if (responseBody != null) {
            return when (originalResponse.code()) {
                401 -> {
                    (currentActivity as Navigator?)?.logOut()

                    originalResponse
                }
                422, 404, 500 -> {
                    val jsonString: String = responseBody.string()
                    var errorMessage = ""

                    try {
                        val responseAsJSON = JSONObject(jsonString)

                        responseAsJSON.keys().forEach {
                            errorMessage = when(it) {
                                "message" -> responseAsJSON.getString("message")
                                else -> responseAsJSON.getJSONArray("base").get(0).toString()
                            }
                        }
                    }
                    catch (e: Exception) {
                        errorMessage = "Unknown error | Ukjent feil"
                    }
                    finally {
                        currentActivity?.runOnUiThread {
                            Toast.makeText(
                                currentActivity,
                                errorMessage,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    originalResponse
                        .newBuilder()
                        .body(ResponseBody.create(contentType, ""))
                        .build()
                }
                else -> originalResponse
            }
        }


        return originalResponse
    }
}
