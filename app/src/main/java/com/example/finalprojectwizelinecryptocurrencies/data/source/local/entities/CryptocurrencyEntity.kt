package com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book

@Entity
data class CryptocurrencyEntity(
    @PrimaryKey
    val book: String,
    val nameCrypto: String? = null,
    val image: Int? = null,
    val maximum_price: String? = null,
    val minimum_price: String? = null
)

fun CryptocurrencyEntity.toBook(): Book =
    Book(
        book = book,
        nameCrypto = nameCrypto,
        image = image,
        maximum_price = maximum_price,
        minimum_price = minimum_price
    )

fun Book.toCryptocurrencyEntity(): CryptocurrencyEntity =
    CryptocurrencyEntity(
        book = this.book ?: "",
        nameCrypto = nameCrypto,
        image = image,
        maximum_price = maximum_price,
        minimum_price = minimum_price
    )
