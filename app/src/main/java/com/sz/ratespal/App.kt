package com.sz.ratespal

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        var token: String? = null
    }

    override fun onCreate() {
        super.onCreate()
    }
}