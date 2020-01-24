package com.android.imagecaching.utils

import android.util.Log

class AppLog {

    companion object {
        fun d(message: String) {
            if (IS_TEST_BUILD && message.isNotBlank()) {
                Log.d(AppLog::class.java.name, "====>$message")
            }
        }
    }
}