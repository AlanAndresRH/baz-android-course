package com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.AskBid

@Entity
data class AsksBookEntity(
    @PrimaryKey(
        autoGenerate = true
    )
    val idAsk: Int = 0,
    val book: String,
    val amount: String? = null,
    val price: String? = null
)

fun AsksBookEntity.toAskBid(): AskBid =
    AskBid(
        amount = amount ?: "0.0",
        book = book,
        price = price ?: "0.0"
    )