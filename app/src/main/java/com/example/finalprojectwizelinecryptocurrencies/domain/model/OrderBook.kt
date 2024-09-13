package com.example.finalprojectwizelinecryptocurrencies.domain.model

import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.OrderBookEntity

data class OrderBook(
    val asks: List<AskBid>,
    val bids: List<AskBid>,
    val sequence: String,
    val updated_at: String
)

fun OrderBook.toOrderBookEntity(book: String): OrderBookEntity {
    return OrderBookEntity(
        book = book,
        sequence = sequence,
        updated_at = updated_at
    )
}
