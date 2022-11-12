package com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.repositories

import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.BookLocalDataSource
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.CryptocurrencyApi
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.toAskBid
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.toBook
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.toBookDetail
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.dto.toBookDetail
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.dto.toListBooks
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.dto.toListOrderBook
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.*
import com.example.finalprojectwizelinecryptocurrencies.dominian.repositories.CryptocurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptocurrencyRepositoryImpl @Inject constructor(
    private val api: CryptocurrencyApi,
    private val bookLocalDataSource: BookLocalDataSource,
) : CryptocurrencyRepository {

    override suspend fun getBooks(): Result<List<Book>> = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            val cryptocurrency = api.getBooks()?.toListBooks() ?: emptyList()
            val cryptocurrencyEntities = cryptocurrency.map {
                it.toBookEntity()
            }

            bookLocalDataSource.insertBookEntity(cryptocurrencyEntities)
            cryptocurrency
        }.recoverCatching {
            bookLocalDataSource.getBook().map {
                it.toBook()
            }
        }
    }

    override suspend fun getDetail(book: String): Result<BookDetail> = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            val bookDetail = api.getDetailBook(book).toBookDetail()
            val bookDetailEntities = bookDetail.toBookDetailEntity()

            bookLocalDataSource.insertBookDetail(bookDetailEntities)
            bookDetail
        }.recoverCatching {
            bookLocalDataSource.getBookDetail(book).toBookDetail()
        }
    }

    override suspend fun getOrderBook(book: String): Result<OrderBook> =
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val orderBook = api.getOrderBook(book).toListOrderBook()
                val orderBookEntity = orderBook.toOrderBookEntity(book)

                bookLocalDataSource.updateOrderBookEntity(
                    orderBookEntity,
                    orderBook.asks.map { it.toAsksBookEntity() },
                    orderBook.bids.map { it.toBidsBookEntity() })
                orderBook
            }.recoverCatching {
                val orderBook = bookLocalDataSource.getOrderBook(book)
                    ?: throw Exception("No se encontró en order book con el parámetro $book")

                val askBook = bookLocalDataSource.getAsksBook(book)
                    ?: throw Exception("No se encontró en order book con el parámetro $book")

                val bidsBook = bookLocalDataSource.getBidsBook(book)
                    ?: throw Exception("No se encontró en order book con el parámetro $book")

                OrderBook(
                    asks = askBook.map { it.toAskBid() },
                    bids = bidsBook.map { it.toAskBid() },
                    sequence = orderBook.sequence ?: "",
                    updated_at = orderBook.updated_at ?: ""
                )
            }
        }
}