package com.example.finalprojectwizelinecryptocurrencies.ui.state

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.BookDetail

data class DetailState(
    val book: BookDetail = BookDetail(),
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)
