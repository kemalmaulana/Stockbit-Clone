package com.example.stockbittest.data

import com.example.stockbittest.data.response.ResponseTotalTopTierVolFull
import com.example.stockbittest.helper.Resource
import javax.inject.Inject

class StockbitRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : StockbitRepository {

    override suspend fun getTopTierVolumeFull(
        limit: Int,
        tsym: String
    ): Resource<ResponseTotalTopTierVolFull> {
        return try {
            val response = apiService.getTopTierVolumeFull(limit, tsym)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Something went wrong")
        }
    }
}