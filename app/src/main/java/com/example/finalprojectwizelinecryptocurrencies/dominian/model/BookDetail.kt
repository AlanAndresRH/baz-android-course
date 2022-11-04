package com.example.finalprojectwizelinecryptocurrencies.dominian.model

import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.BookDetailEntity

data class BookDetail(
    val book: String,
    val nameCrypto: String,
    val high: String,
    val volume: String,
    val createdAt: String,
    val image: Int
)

fun BookDetail.toBookDetailEntity(): BookDetailEntity =
    BookDetailEntity(
        book = this.book,
        nameCrypto = nameCrypto,
        high = high,
        volume = volume,
        createdAt = createdAt,
        image = image
    )