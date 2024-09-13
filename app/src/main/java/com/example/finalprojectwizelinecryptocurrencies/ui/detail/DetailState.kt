package com.example.finalprojectwizelinecryptocurrencies.ui.detail

import com.example.finalprojectwizelinecryptocurrencies.domain.model.BookDetail
import com.example.finalprojectwizelinecryptocurrencies.domain.model.OrderBook

data class DetailState(
    val book: BookDetail? = null,
    val orderBook: OrderBook? = null,
    val isLoading: Boolean = false,
    val showData: Boolean = false,
    val errorMsg: String? = null,
    val askBookListEmpty: Boolean = false,
    val bidBookListEmpty: Boolean = false,
    val showErrorData: Boolean = false
)
