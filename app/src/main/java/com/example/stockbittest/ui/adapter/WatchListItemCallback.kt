package com.example.stockbittest.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.stockbittest.data.response.ResponseTotalTopTierVolFull

object WatchListItemCallback: DiffUtil.ItemCallback<ResponseTotalTopTierVolFull.Data>() {
    override fun areItemsTheSame(
        oldItem: ResponseTotalTopTierVolFull.Data,
        newItem: ResponseTotalTopTierVolFull.Data
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ResponseTotalTopTierVolFull.Data,
        newItem: ResponseTotalTopTierVolFull.Data
    ): Boolean {
        return oldItem.coinInfo?.id == newItem.coinInfo?.id
    }

}