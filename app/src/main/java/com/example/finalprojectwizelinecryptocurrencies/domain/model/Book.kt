package com.example.finalprojectwizelinecryptocurrencies.domain.model

import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.BookEntity

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
