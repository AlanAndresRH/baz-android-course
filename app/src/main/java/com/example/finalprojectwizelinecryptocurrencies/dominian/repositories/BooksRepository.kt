package com.example.finalprojectwizelinecryptocurrencies.dominian.repositories

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.BookDetail

interface CryptocurrencyRepository {

    suspend fun getBooks(): Result<List<Book>>

    suspend fun getDetail(book: String): Result<BookDetail>
}