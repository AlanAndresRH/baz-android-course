package com.example.finalprojectwizelinecryptocurrencies.ui.state

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.BookDetail
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.OrderBook

data class DetailState(
    val book: BookDetail? = null,
    val orderBook: OrderBook? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val askBookListEmpty: Boolean = false,
    val bidBookListEmpty: Boolean = false,
    val showErrorData: Boolean = false
)