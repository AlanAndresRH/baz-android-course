package com.example.finalprojectwizelinecryptocurrencies.ui.state

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter

data class HomeState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val keyFilter: KeyFilter = KeyFilter.FILTER_MXN,
    val errorMsg: String? = null
)
