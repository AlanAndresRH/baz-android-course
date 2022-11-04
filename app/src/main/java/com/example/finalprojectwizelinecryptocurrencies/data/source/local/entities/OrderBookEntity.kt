package com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.OrderBook

@Entity
data class OrderBookEntity(
    @PrimaryKey
    val book: String,
    val sequence: String? = null,
    val updated_at: String? = null,
)