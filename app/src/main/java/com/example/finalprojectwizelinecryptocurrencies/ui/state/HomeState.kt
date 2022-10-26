package com.example.finalprojectwizelinecryptocurrencies.ui.state

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book

data class HomeState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false
)
