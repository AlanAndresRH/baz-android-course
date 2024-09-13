package com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote

import javax.inject.Inject

class BookRemoteDataSource @Inject constructor(
    private val api: CryptocurrencyApi
) {
    suspend fun getBooks() = api.getBooks()

    suspend fun getDetailBook(book: String) = api.getDetailBook(book)

    suspend fun getOrderBook(book: String) = api.getOrderBook(book)

    fun getBooksRxJava() = api.getBooksRxJava()
}
