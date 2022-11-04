package com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.BookDetailEntity

@Dao
interface BookDetailDao {

    @Query("SELECT * FROM bookDetailEntity WHERE book = :book")
    suspend fun getBookDetail(book: String): BookDetailEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookDetail(bookDetail: BookDetailEntity)
}