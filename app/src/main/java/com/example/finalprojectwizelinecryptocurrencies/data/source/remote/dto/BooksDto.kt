package com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto

import com.example.finalprojectwizelinecryptocurrencies.R
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book
import com.example.finalprojectwizelinecryptocurrencies.utils.ICON_BOOK
import com.example.finalprojectwizelinecryptocurrencies.utils.NAME_BOOK
import com.example.finalprojectwizelinecryptocurrencies.utils.formatCurrency

data class BooksDto(
    val payload: List<Payload>,
    val success: Boolean
)

fun BooksDto.toListBooks(): List<Book> {
    val resultEntries = payload.mapIndexed { _, entries ->
        val bookSplit = entries.book?.split("_")

        Book(
            book = entries.book,
            nameCrypto = NAME_BOOK[bookSplit?.get(0)] ?: "Unknown",
            image = ICON_BOOK[bookSplit?.get(0)] ?: R.drawable.ic_btc,
            minimum_price = entries.minimum_price?.formatCurrency(bookSplit?.get(1) ?: "MXN"),
            maximum_price = entries.maximum_price?.formatCurrency(bookSplit?.get(1) ?: "MXN")
        )
    }

    return resultEntries
}