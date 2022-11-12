package com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local

import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.dao.BookDao
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.dao.BookDetailDao
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.dao.OrderBookDao
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.*
import javax.inject.Inject

class BookLocalDataSource @Inject constructor(
    private val bookDao: BookDao,
    private val bookDetailDao: BookDetailDao,
    private val orderBookDao: OrderBookDao
) {

    suspend fun insertBookEntity(cryptocurrencyList: List<BookEntity>) =
        bookDao.insertBookEntity(cryptocurrencyList)

    suspend fun getBook(): List<BookEntity> = bookDao.getBook()

    suspend fun insertBookDetail(bookDetail: BookDetailEntity) =
        bookDetailDao.insertBookDetail(bookDetail)

    suspend fun getBookDetail(book: String): BookDetailEntity = bookDetailDao.getBookDetail(book)

    suspend fun updateOrderBookEntity(
        orderBook: OrderBookEntity,
        asksBookEntity: List<AsksBookEntity>,
        bidsBookEntity: List<BidsBookEntity>
    ) {
        orderBookDao.insertOrderBookEntity(orderBook)

        orderBookDao.deleteAskEntity(orderBook.book)
        orderBookDao.insertAsksEntity(asksBookEntity)

        orderBookDao.deleteBidsEntity(orderBook.book)
        orderBookDao.insertBidsEntity(bidsBookEntity)
    }

    suspend fun getOrderBook(book: String) = orderBookDao.getOrderBook(book)

    suspend fun getAsksBook(book: String) = orderBookDao.getAskBook(book)

    suspend fun getBidsBook(book: String) = orderBookDao.getBidBook(book)
}