package com.example.stockbittest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockbittest.data.StockbitRepository
import com.example.stockbittest.data.response.ResponseTotalTopTierVolFull
import com.example.stockbittest.helper.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class StockbitViewModel @Inject constructor(
    private val repository: StockbitRepository
): ViewModel() {

    sealed class StockbitEvent {
        class Success(val result: ResponseTotalTopTierVolFull): StockbitEvent()
        class Failure(val errorText: String): StockbitEvent()
        object Loading: StockbitEvent()
        object Empty: StockbitEvent()
    }

    private val _getTopTierVolumeFull = MutableStateFlow<StockbitEvent>(StockbitEvent.Empty)
    val getTopTierVolumeFull: StateFlow<StockbitEvent>
        get() = _getTopTierVolumeFull

    fun getVolumeFull(
        limit: Int, tsym: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _getTopTierVolumeFull.value = StockbitEvent.Loading
            when(val response = repository.getTopTierVolumeFull(limit, tsym)) {
                is Resource.Error -> _getTopTierVolumeFull.value = StockbitEvent.Failure(response.message!!)
                is Resource.Success -> {
                    val volumeFull = response.data
                    if(volumeFull != null) {
                        _getTopTierVolumeFull.value = StockbitEvent.Success(volumeFull)
                    }
                }
            }
        }
    }

}