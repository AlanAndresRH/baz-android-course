package com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
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
        nameCrypto = nameCrypto,
        high = high,
        volume = volume,
        createdAt = createdAt,
        image = image
    )

fun BookDetail.toBookDetailEntity(): BookDetailEntity =
    BookDetailEntity(
        book = this.book ?: "",
        nameCrypto = nameCrypto,
        high = high,
        volume = volume,
        createdAt = createdAt,
        image = image
    )
