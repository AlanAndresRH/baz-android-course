package com.example.finalprojectwizelinecryptocurrencies.data.source.local.dababase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.BookDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.BookDetailDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.OrderBookDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.*

@Database(
    entities = [BookEntity::class, BookDetailEntity::class, OrderBookEntity::class, AsksBookEntity::class, BidsBookEntity::class],
    version = 1
)
abstract class CryptocurrencyDataBase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    abstract fun bookDetailDao(): BookDetailDao

    abstract fun orderBookDao(): OrderBookDao
}