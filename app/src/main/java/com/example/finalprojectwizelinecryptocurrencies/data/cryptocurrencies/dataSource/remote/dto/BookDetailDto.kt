package com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.dto

import com.example.finalprojectwizelinecryptocurrencies.R
import com.example.finalprojectwizelinecryptocurrencies.domain.model.BookDetail
import com.example.finalprojectwizelinecryptocurrencies.utils.ICON_BOOK
import com.example.finalprojectwizelinecryptocurrencies.utils.NAME_BOOK
import com.example.finalprojectwizelinecryptocurrencies.utils.formatDate

data class BookDetailDto(
    val payload: PayloadDetailDto,
    val success: Boolean
)

fun BookDetailDto.toBookDetail(): BookDetail {
    val bookSplit = payload.book?.split("_")
    return BookDetail(
        nameCrypto = NAME_BOOK[bookSplit?.get(0)] ?: "Unknown",
        book = payload.book ?: "Unknown",
        high = payload.high ?: "0.00",
        volume = payload.volume ?: "0.00",
        createdAt = payload.created_at.formatDate(),
        image = ICON_BOOK[bookSplit?.get(0)] ?: R.drawable.ic_coin_unknown
    )
}
