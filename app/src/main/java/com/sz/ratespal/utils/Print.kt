package com.sz.ratespal.utils

import android.util.Log
import com.sz.ratespal.BuildConfig

class Print {

    companion object {
        fun i(data: Any?) {
            if (BuildConfig.DEBUG) {
                Log.i("FATAL", data.toString())
            }
        }
    }

}