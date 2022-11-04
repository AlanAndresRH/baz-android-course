package com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.OrderBook

data class OrderBookWithAskBidEntity(
    val book: OrderBookEntity,
    val askList: List<AsksBookEntity>,
    val bidList: List<BidsBookEntity>
)

fun OrderBookWithAskBidEntity.toOrderBook(): OrderBook {
    return OrderBook(
        asks = this.askList.map {
            it.toAskBidBook()
        },
        bids = this.bidList.map {
            it.toAskBidBook()
        },
        sequence = book.sequence ?: "Unknown",
        updated_at = book.updated_at ?: "0000/00/00"
    )
}