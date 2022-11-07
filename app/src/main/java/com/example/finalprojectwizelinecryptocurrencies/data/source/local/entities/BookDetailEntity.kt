package com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalprojectwizelinecryptocurrencies.R
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.BookDetail

@Entity
data class BookDetailEntity(
    @PrimaryKey
    val book: String,
    val nameCrypto: String? = null,
    val high: String? = null,
    val volume: String? = null,
    val createdAt: String? = null,
    val image: Int? = null
)

fun BookDetailEntity.toBookDetail(): BookDetail =
    BookDetail(
        book = book,
        nameCrypto = nameCrypto ?: "Unknown",
        high = high ?: "0.00",
        volume = volume ?: "0.00",
        createdAt = createdAt ?: "0000/00/00",
        image = image ?: R.drawable.ic_coin_unknown
    )