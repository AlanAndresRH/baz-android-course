package com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book

data class BooksDto(
    val payload: List<Payload>,
    val success: Boolean
)


fun BooksDto.toListBooks(): List<Book> {
    val resultEntries = payload.mapIndexed { _, entries ->
        Book(
            book = entries.book,
            minimum_price = entries.minimum_price,
            maximum_price = entries.maximum_price
        )
    }

    return resultEntries
}