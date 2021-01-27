package com.example.stockbittest.di

import android.content.Context
import com.example.stockbittest.ViewModelProviderFactory
import com.example.stockbittest.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideConnectivityInterceptor(
        @ApplicationContext context: Context
    ): ConnectivityInterceptor = ConnectivityInterceptorImpl(context)

    @Singleton
    @Provides
    fun provideApiService(
        connectivityInterceptor: ConnectivityInterceptor,
        @ApplicationContext context: Context
    ): ApiService = ApiService.invoke(connectivityInterceptor, context)

    @Singleton
    @Provides
    fun provideStockbitRepository(
        apiService: ApiService
    ): StockbitRepository = StockbitRepositoryImpl(apiService)

    @Provides
    fun provideViewModelFactory(
        stockbitRepository: StockbitRepository
    ): ViewModelProviderFactory = ViewModelProviderFactory(stockbitRepository)
}