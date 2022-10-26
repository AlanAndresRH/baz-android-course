package com.example.finalprojectwizelinecryptocurrencies.dominian.repositories

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book
import kotlinx.coroutines.flow.Flow
import com.example.finalprojectwizelinecryptocurrencies.data.ResultApi

interface CryptocurrencyRepository {

    fun getBooks(): Flow<ResultApi<List<Book>>>

    suspend fun getBooksNew(): Result<List<Book>>
}