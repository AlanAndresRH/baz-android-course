package com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto

import com.example.finalprojectwizelinecryptocurrencies.R
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.BookDetail
import com.example.finalprojectwizelinecryptocurrencies.utils.ICON_BOOK
import com.example.finalprojectwizelinecryptocurrencies.utils.NAME_BOOK

data class BookDetailDto(
    val payload: PayloadDetail,
    val success: Boolean
)

fun BookDetailDto.toBookDetail(): BookDetail {
    val bookSplit = payload.book?.split("_")
    return BookDetail(
        nameCrypto = NAME_BOOK[bookSplit?.get(0)] ?: "Unknown",
        book = payload.book,
        high = payload.high,
        volume = payload.volume,
        createdAt = payload.created_at,
        image = ICON_BOOK[bookSplit?.get(0)] ?: R.drawable.ic_btc
    )
}
