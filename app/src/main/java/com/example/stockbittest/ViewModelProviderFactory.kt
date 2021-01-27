package com.example.stockbittest

import androidx.lifecycle.ViewModelProvider
import com.example.stockbittest.data.StockbitRepository
import javax.inject.Inject

class ViewModelProviderFactory @Inject constructor(
    private val stockbitRepository: StockbitRepository
): ViewModelProvider.NewInstanceFactory() {

}