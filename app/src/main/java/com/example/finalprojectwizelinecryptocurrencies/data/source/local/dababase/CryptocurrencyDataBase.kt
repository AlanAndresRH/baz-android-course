package com.example.finalprojectwizelinecryptocurrencies.data.source.local.dababase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.CryptocurrencyDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.BookDetailDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.BookDetailEntity
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.CryptocurrencyEntity

@Database(
    entities = [CryptocurrencyEntity::class, BookDetailEntity::class],
    version = 1
)
abstract class CryptocurrencyDataBase : RoomDatabase() {

    abstract fun cryptocurrencyDao(): CryptocurrencyDao

    abstract fun bookDetailDao(): BookDetailDao
}