package com.example.stockbittest.helper

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast


fun String.makeToast(context: Context) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
    }
}

