package com.example.finalprojectwizelinecryptocurrencies.data.source.remote

import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.BookDetailDto
import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.BooksDto
import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.OrderBookDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptocurrencyApi {

    @GET("available_books/")
    suspend fun getBooks(): BooksDto

    @GET("ticker/")
    suspend fun getDetailBook(
        @Query("book") book: String
    ): BookDetailDto

    @GET("order_book")
    suspend fun getOrderBook(
        @Query("book") book: String
    ): OrderBookDto
}