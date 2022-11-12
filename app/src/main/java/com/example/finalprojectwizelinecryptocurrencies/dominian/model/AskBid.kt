package com.example.finalprojectwizelinecryptocurrencies.dominian.model

import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.AsksBookEntity
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.BidsBookEntity

data class AskBid(
    val amount: String,
    val book: String,
    val price: String
)

fun AskBid.toAsksBookEntity(): AsksBookEntity {
    return AsksBookEntity(
        amount = amount,
        book = this.book,
        price = price
    )
}

fun AskBid.toBidsBookEntity() : BidsBookEntity {
    return BidsBookEntity(
        amount = amount,
        book = this.book,
        price = price
    )
}