package com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.BookEntity

@Dao
interface BookDao {

    @Query("SELECT * FROM bookEntity")
    suspend fun getBook(): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookEntity(cryptocurrencyList: List<BookEntity>)
}