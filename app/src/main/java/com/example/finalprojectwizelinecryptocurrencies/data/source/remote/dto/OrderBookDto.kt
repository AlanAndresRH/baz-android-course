package com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.AsksBidsBook
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.OrderBook
import com.example.finalprojectwizelinecryptocurrencies.utils.formatCurrency

data class OrderBookDto(
    val payload: PayloadOrder,
    val success: Boolean
)

fun OrderBookDto.toListOrderBook(): OrderBook {
    val askList = payload.asks?.map {
        val bookSplit = it.book?.split("_")
        AsksBidsBook(
            amount = it.amount.formatCurrency(bookSplit?.get(1) ?: "MXN"),
            book = it.book,
            price = it.price.formatCurrency(bookSplit?.get(1) ?: "MXN")
        )
    }?.toList()

    val bidList = payload.bids?.map {
        val bookSplit = it.book?.split("_")
        AsksBidsBook(
            amount = it.amount.formatCurrency(bookSplit?.get(1) ?: "MXN"),
            book = it.book,
            price = it.price.formatCurrency(bookSplit?.get(1) ?: "MXN")
        )
    }?.toList()

    return OrderBook(
        asks = askList,
        bids = bidList,
        sequence = payload.sequence,
        updated_at = payload.updated_at
    )
}