package com.example.finalprojectwizelinecryptocurrencies.data.repositories

import com.example.finalprojectwizelinecryptocurrencies.data.source.CryptocurrencyApi
import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.toBookDetail
import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.toListBooks
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.BookDetail
import com.example.finalprojectwizelinecryptocurrencies.dominian.repositories.CryptocurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: CryptocurrencyApi
) : CryptocurrencyRepository {

    override suspend fun getBooks(): Result<List<Book>> = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            api.getBooks().toListBooks()
        }
    }

    override suspend fun getDetail(book: String): Result<BookDetail> = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            api.getDetailBook(book).toBookDetail()
        }
    }
}