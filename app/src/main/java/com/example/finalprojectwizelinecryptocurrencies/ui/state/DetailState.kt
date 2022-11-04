package com.example.finalprojectwizelinecryptocurrencies.ui.state

import com.example.finalprojectwizelinecryptocurrencies.R
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.BookDetail
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.OrderBook

data class DetailState(
    val book: BookDetail = BookDetail("", "", "", "", "", R.drawable.ic_coin_error),
    val orderBook: OrderBook = OrderBook(emptyList(), emptyList(), "", ""),
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val askBookListEmpty: Boolean = false,
    val bidBookListEmpty: Boolean = false
)
