package com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.dababase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.dao.BookDao
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.dao.BookDetailDao
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.dao.OrderBookDao
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.entities.*

@Database(
    entities = [BookEntity::class, BookDetailEntity::class, OrderBookEntity::class, AsksBookEntity::class, BidsBookEntity::class],
    version = 1
)
abstract class CryptocurrencyDataBase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    abstract fun bookDetailDao(): BookDetailDao

    abstract fun orderBookDao(): OrderBookDao
}
