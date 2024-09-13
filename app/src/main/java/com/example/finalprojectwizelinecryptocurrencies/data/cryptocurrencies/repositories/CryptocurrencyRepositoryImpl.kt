package com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.repositories

import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.BookLocalDataSource
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.toAskBid
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.toBook
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.toBookDetail
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.BookRemoteDataSource
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.dto.toBookDetail
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.dto.toListBooks
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.dto.toListOrderBook
import com.example.finalprojectwizelinecryptocurrencies.data.network.NetworkMonitor
import com.example.finalprojectwizelinecryptocurrencies.di.IODispatcher
import com.example.finalprojectwizelinecryptocurrencies.di.IOScheduler
import com.example.finalprojectwizelinecryptocurrencies.domain.model.*
import com.example.finalprojectwizelinecryptocurrencies.domain.repositories.CryptocurrencyRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.rx2.rxSingle
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptocurrencyRepositoryImpl @Inject constructor(
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val bookLocalDataSource: BookLocalDataSource,
    private val networkMonitor: NetworkMonitor,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
    @IOScheduler private val scheduler: Scheduler
) : CryptocurrencyRepository {

    override suspend fun getBooks(): Result<List<Book>> = withContext(dispatcher) {
        if (networkMonitor.isOnline()) {
            kotlin.runCatching {
                val cryptocurrency = bookRemoteDataSource.getBooks()?.toListBooks() ?: emptyList()
                val cryptocurrencyEntities = cryptocurrency.map {
                    it.toBookEntity()
                }

                bookLocalDataSource.insertBookEntity(cryptocurrencyEntities)
                cryptocurrency
            }
        } else
            kotlin.runCatching { bookLocalDataSource.getBook().map { it.toBook() } }
    }

    override suspend fun getDetail(book: String): Result<BookDetail> = withContext(dispatcher) {
        if (networkMonitor.isOnline()) {
            kotlin.runCatching {
                val bookDetail = bookRemoteDataSource.getDetailBook(book).toBookDetail()
                val bookDetailEntities = bookDetail.toBookDetailEntity()

                bookLocalDataSource.insertBookDetail(bookDetailEntities)
                bookDetail
            }
        } else
            kotlin.runCatching { bookLocalDataSource.getBookDetail(book).toBookDetail() }
    }

    override suspend fun getOrderBook(book: String): Result<OrderBook> =
        withContext(dispatcher) {
            if (networkMonitor.isOnline()) {
                kotlin.runCatching {
                    val orderBook = bookRemoteDataSource.getOrderBook(book).toListOrderBook()
                    val orderBookEntity = orderBook.toOrderBookEntity(book)

                    bookLocalDataSource.updateOrderBookEntity(
                        orderBookEntity,
                        orderBook.asks.map { it.toAsksBookEntity() },
                        orderBook.bids.map { it.toBidsBookEntity() }
                    )
                    orderBook
                }
            } else {
                kotlin.runCatching {
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

    override fun getBooksRxJava(): Single<List<Book>> {
        return if (networkMonitor.isOnline()) {
            bookRemoteDataSource.getBooksRxJava().map { it.toListBooks() }.doOnSuccess {
                val cryptocurrencyEntities =
                    it.map { bookList -> bookList.toBookEntity() }
                bookLocalDataSource.insertBookEntity2(cryptocurrencyEntities)
            }.subscribeOn(Schedulers.io())
        } else
            rxSingle { bookLocalDataSource.getBook().map { it.toBook() } }.subscribeOn(scheduler)
    }
}
