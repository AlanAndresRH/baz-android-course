package com.example.finalprojectwizelinecryptocurrencies.ui.home

import com.example.finalprojectwizelinecryptocurrencies.domain.model.Book
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter

data class HomeState(
    val books: List<Book> = emptyList(),
    val isLoading: Boolean = false,
    val keyFilter: KeyFilter = KeyFilter.FILTER_MXN,
    val errorMsg: String? = null,
)

fun HomeState.showMexico() = keyFilter == KeyFilter.FILTER_MXN

fun HomeState.showAllCountry() = keyFilter == KeyFilter.NO_FILTER

fun HomeState.showErrorData() = !errorMsg.isNullOrEmpty()
