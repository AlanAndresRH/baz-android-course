package com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.dto

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.AskBid
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.OrderBook
import com.example.finalprojectwizelinecryptocurrencies.utils.formatCurrency

data class OrderBookDto(
    val payload: PayloadOrderDto,
    val success: Boolean
)

fun OrderBookDto.toListOrderBook(): OrderBook {
    val askList = payload.asks?.map {
        val bookSplit = it.book?.split("_")
        AskBid(
            amount = it.amount.formatCurrency(bookSplit?.get(1) ?: "MXN"),
            book = it.book ?: "Unknown",
            price = it.price.formatCurrency(bookSplit?.get(1) ?: "MXN")
        )
    }?.toList()

    val bidList = payload.bids?.map {
        val bookSplit = it.book?.split("_")
        AskBid(
            amount = it.amount.formatCurrency(bookSplit?.get(1) ?: "MXN"),
            book = it.book ?: "Unknown",
            price = it.price.formatCurrency(bookSplit?.get(1) ?: "MXN")
        )
    }?.toList()

    return OrderBook(
        asks = askList ?: emptyList(),
        bids = bidList ?: emptyList(),
        sequence = payload.sequence ?: "Unknown",
        updated_at = payload.updated_at ?: "0000/00/00"
    )
}