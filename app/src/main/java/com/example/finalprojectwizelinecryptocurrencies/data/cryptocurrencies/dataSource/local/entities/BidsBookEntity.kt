package com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalprojectwizelinecryptocurrencies.domain.model.AskBid

@Entity
data class BidsBookEntity(
    @PrimaryKey(
        autoGenerate = true
    )
    val idBid: Int = 0,
    val book: String,
    val amount: String? = null,
    val price: String? = null
)

fun BidsBookEntity.toAskBid(): AskBid =
    AskBid(
        amount = amount ?: "0.00",
        book = book,
        price = price ?: "0.0"
    )
