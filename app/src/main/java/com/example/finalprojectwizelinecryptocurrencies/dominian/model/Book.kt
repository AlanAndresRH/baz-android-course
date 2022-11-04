package com.example.finalprojectwizelinecryptocurrencies.dominian.model

import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.BookEntity

data class Book(
    val book: String,
    val nameCrypto: String,
    val image: Int,
    val maximum_price: String,
    val minimum_price: String
)

fun Book.toBookEntity(): BookEntity =
    BookEntity(
        book = this.book,
        nameCrypto = nameCrypto,
        image = image,
        maximum_price = maximum_price,
        minimum_price = minimum_price
    )