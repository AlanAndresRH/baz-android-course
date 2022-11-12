package com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.AsksBookEntity
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.BidsBookEntity
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.OrderBookEntity

@Dao
interface OrderBookDao {
    @Query("SELECT * FROM orderBookEntity WHERE book = :book")
    suspend fun getOrderBook(book: String): OrderBookEntity?

    @Query("SELECT * FROM asksBookEntity WHERE book = :book")
    suspend fun getAskBook(book: String): List<AsksBookEntity>?

    @Query("SELECT * FROM bidsBookEntity WHERE book = :book")
    suspend fun getBidBook(book: String): List<BidsBookEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderBookEntity(orderBook: OrderBookEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsksEntity(asksBookEntity: List<AsksBookEntity>)

    @Query("DELETE FROM asksBookEntity WHERE book = :book")
    suspend fun deleteAskEntity(book: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBidsEntity(bidsBookEntity: List<BidsBookEntity>)

    @Query("DELETE FROM bidsBookEntity WHERE book = :book")
    suspend fun deleteBidsEntity(book: String)
}