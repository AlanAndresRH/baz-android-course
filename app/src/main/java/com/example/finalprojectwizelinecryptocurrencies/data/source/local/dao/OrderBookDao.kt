package com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao

import androidx.room.Dao
import androidx.room.Index.Order
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.AsksBookEntity
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.BidsBookEntity
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.OrderBookEntity
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.OrderBookWithAskBidEntity
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.AskBid
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.OrderBook

@Dao
interface OrderBookDao {

    /*@Query(
        """
        SELECT * FROM orderBookEntity
        JOIN asksBookEntity ON orderBookEntity.book = asksBookEntity.book 
        JOIN bidsBookEntity ON orderBookEntity.book = bidsBookEntity.book
        WHERE orderBookEntity.book = :book 
        """
    )*/

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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBidsEntity(bidsBookEntity: List<BidsBookEntity>)
}