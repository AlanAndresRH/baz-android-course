package com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.entities.CryptocurrencyEntity

@Dao
interface CryptocurrencyDao {

    @Query("SELECT * FROM cryptocurrencyEntity")
    suspend fun getCryptocurrencies(): List<CryptocurrencyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptocurrencies(cryptocurrencyList: List<CryptocurrencyEntity>)
}