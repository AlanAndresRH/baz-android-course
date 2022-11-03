package com.example.finalprojectwizelinecryptocurrencies.data.repositories

import com.example.finalprojectwizelinecryptocurrencies.data.source.CryptocurrencyApi
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.BookDetailDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.CryptocurrencyDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.toBook
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.toBookDetail
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.toBookDetailEntity
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.toCryptocurrencyEntity
import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.toBookDetail
import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.toListBooks
import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.toListOrderBook
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.BookDetail
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.OrderBook
import com.example.finalprojectwizelinecryptocurrencies.dominian.repositories.CryptocurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: CryptocurrencyApi,
    private val cryptocurrencyDao: CryptocurrencyDao,
    private val bookDetailDao: BookDetailDao
) : CryptocurrencyRepository {

    override suspend fun getBooks(): Result<List<Book>> = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            val cryptocurrency = api.getBooks().toListBooks()
            val cryptocurrencyEntities = cryptocurrency.map {
                it.toCryptocurrencyEntity()
            }
            cryptocurrencyDao.insertCryptocurrencies(cryptocurrencyEntities)
            cryptocurrency
        }.recoverCatching {
            cryptocurrencyDao.getCryptocurrencies().map {
                it.toBook()
            }
        }
    }

    override suspend fun getDetail(book: String): Result<BookDetail> = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            val bookDetail = api.getDetailBook(book).toBookDetail()
            val bookDetailEntities = bookDetail.toBookDetailEntity()

            bookDetailDao.insertBookDetail(bookDetailEntities)
            bookDetail
        }.recoverCatching {
            bookDetailDao.getBookDetail().toBookDetail()
        }
    }

    override suspend fun getOrderBook(book: String): Result<OrderBook> =
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                api.getOrderBook(book).toListOrderBook()
            }
        }
}