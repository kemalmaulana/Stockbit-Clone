package com.example.stockbittest.data

import com.example.stockbittest.data.response.ResponseTotalTopTierVolFull
import com.example.stockbittest.helper.Resource

interface StockbitRepository {
    suspend fun getTopTierVolumeFull(limit: Int, tsym: String): Resource<ResponseTotalTopTierVolFull>
}