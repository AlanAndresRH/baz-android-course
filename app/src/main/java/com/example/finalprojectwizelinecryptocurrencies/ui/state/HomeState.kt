package com.example.finalprojectwizelinecryptocurrencies.ui.state

import com.example.finalprojectwizelinecryptocurrencies.domain.model.Book
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter

data class HomeState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val keyFilter: KeyFilter = KeyFilter.FILTER_MXN,
    val showMexico: Boolean = false,
    val showAllCountry: Boolean = false,
    val errorMsg: String? = null,
    val showErrorData: Boolean = false
)
