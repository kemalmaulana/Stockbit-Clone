package com.example.stockbittest.data

import android.content.Context
import com.example.stockbittest.data.response.ResponseTotalTopTierVolFull
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ApiService {

    @GET("data/top/totaltoptiervolfull")
    suspend fun getTopTierVolumeFull(
        @Path("limit") limit: Int?,
        @Path("tsym") currencySymbol: String?
    ): Response<ResponseTotalTopTierVolFull>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor, context: Context): ApiService {
            val gson = GsonBuilder().create()

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .addInterceptor(ChuckInterceptor(context))
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://min-api.cryptocompare.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService::class.java)
        }
    }

}