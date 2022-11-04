package com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalprojectwizelinecryptocurrencies.R
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book

@Entity
data class BookEntity(
    @PrimaryKey
    val book: String,
    val nameCrypto: String? = null,
    val image: Int? = null,
    val maximum_price: String? = null,
    val minimum_price: String? = null
)

fun BookEntity.toBook(): Book =
    Book(
        book = book,
        nameCrypto = nameCrypto ?: "Unknown",
        image = image ?: R.drawable.ic_coin_error,
        maximum_price = maximum_price ?: "$ 0.00",
        minimum_price = minimum_price ?: "$ 0.00"
    )