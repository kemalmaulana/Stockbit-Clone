package com.example.stockbittest.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.stockbittest.helper.makeToast
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.net.SocketTimeoutException
import javax.inject.Inject

class ConnectivityInterceptorImpl @Inject constructor(private val context: Context) :
    ConnectivityInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            if(hasInternetConnection()) {
                "Please check Internet Connection".makeToast(context)
            }
        } catch (ste: SocketTimeoutException) {
            Timber.e(ste)
        }
        return chain.proceed(chain.request())
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION")
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    ConnectivityManager.TYPE_VPN -> true
                    else -> false
                }
            }
        }
        return false
    }
}