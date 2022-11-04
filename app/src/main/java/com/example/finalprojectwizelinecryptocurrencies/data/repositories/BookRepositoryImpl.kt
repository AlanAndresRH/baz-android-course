package com.example.finalprojectwizelinecryptocurrencies.data.repositories

import com.example.finalprojectwizelinecryptocurrencies.data.source.CryptocurrencyApi
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.BookDetailDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.BookDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.OrderBookDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.*
import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.toBookDetail
import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.toListBooks
import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.toListOrderBook
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.*
import com.example.finalprojectwizelinecryptocurrencies.dominian.repositories.CryptocurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: CryptocurrencyApi,
    private val bookDao: BookDao,
    private val bookDetailDao: BookDetailDao,
    private val orderBookDao: OrderBookDao
) : CryptocurrencyRepository {

    override suspend fun getBooks(): Result<List<Book>> = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            val cryptocurrency = api.getBooks().toListBooks()
            val cryptocurrencyEntities = cryptocurrency.map {
                it.toBookEntity()
            }
            bookDao.insertCryptocurrencies(cryptocurrencyEntities)
            cryptocurrency
        }.recoverCatching {
            bookDao.getCryptocurrencies().map {
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
            bookDetailDao.getBookDetail(book).toBookDetail()
        }
    }

    override suspend fun getOrderBook(book: String): Result<OrderBook> =
        withContext(Dispatchers.IO) {
            kotlin.runCatching {
                val orderBook = api.getOrderBook(book).toListOrderBook()
                val orderBookEntity = orderBook.toOrderBookEntity(book)

                orderBookDao.insertOrderBookEntity(orderBookEntity)
                orderBookDao.insertAsksEntity(orderBook.asks.map { it.toAsksBookEntity() })
                orderBookDao.insertBidsEntity(orderBook.bids.map { it.toBidsBookEntity() })
                orderBook
            }.recoverCatching {
                val orderBook = orderBookDao.getOrderBook(book)
                    ?: throw Exception("No se encotro en order book con el parametro $book")

                val askBook = orderBookDao.getAskBook(book)
                    ?: throw Exception("No se encotro en order book con el parametro $book")

                val bidsBook = orderBookDao.getBidBook(book)
                    ?: throw Exception("No se encotro en order book con el parametro $book")

                OrderBook(
                    asks = askBook.map { it.toAskBidBook() },
                    bids = bidsBook.map { it.toAskBidBook() },
                    sequence = orderBook.sequence ?: "",
                    updated_at = orderBook.updated_at ?: ""
                )
            }
        }
}