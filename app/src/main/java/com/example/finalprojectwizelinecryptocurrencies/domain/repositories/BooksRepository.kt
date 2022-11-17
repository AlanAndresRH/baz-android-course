package com.example.finalprojectwizelinecryptocurrencies.domain.repositories

import com.example.finalprojectwizelinecryptocurrencies.domain.model.Book
import com.example.finalprojectwizelinecryptocurrencies.domain.model.BookDetail
import com.example.finalprojectwizelinecryptocurrencies.domain.model.OrderBook
import io.reactivex.Single

interface CryptocurrencyRepository {

    suspend fun getBooks(): Result<List<Book>>

    suspend fun getDetail(book: String): Result<BookDetail>

    suspend fun getOrderBook(book: String): Result<OrderBook>

    fun getBooksRxJava(): Single<List<Book>>
}
