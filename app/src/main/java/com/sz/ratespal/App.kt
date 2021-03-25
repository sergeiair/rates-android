package com.sz.ratespal

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.HiltAndroidApp
import java.lang.ref.WeakReference

@HiltAndroidApp
class App : Application() {

    companion object {
        var token: String? = null

        var currentActivity: WeakReference<AppCompatActivity>? = null
    }

    override fun onCreate() {
        super.onCreate()
    }
}