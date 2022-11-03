package com.example.finalprojectwizelinecryptocurrencies.dominian.repositories

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.BookDetail
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.OrderBook

interface CryptocurrencyRepository {

    suspend fun getBooks(): Result<List<Book>>

    suspend fun getDetail(book: String): Result<BookDetail>

    suspend fun getOrderBook(book: String): Result<OrderBook>
}