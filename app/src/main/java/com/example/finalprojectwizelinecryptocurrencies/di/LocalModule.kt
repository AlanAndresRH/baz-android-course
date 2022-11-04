package com.example.finalprojectwizelinecryptocurrencies.di

import android.content.Context
import androidx.room.Room
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dababase.CryptocurrencyDataBase
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.BookDetailDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.BookDao
import com.example.finalprojectwizelinecryptocurrencies.data.source.local.dao.OrderBookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext mContext: Context): CryptocurrencyDataBase =
        Room.databaseBuilder(
            mContext,
            CryptocurrencyDataBase::class.java,
            "com.example.finalprojectwizelinecryptocurrencies.cyptocurrecy_database"
        ).build()

    @Provides
    @Singleton
    fun provideCryptocurrencyDao(cryptocurrencyDatabase: CryptocurrencyDataBase): BookDao =
        cryptocurrencyDatabase.bookDao()

    @Provides
    @Singleton
    fun provideBookDetailDao(cryptocurrencyDatabase: CryptocurrencyDataBase): BookDetailDao =
        cryptocurrencyDatabase.bookDetailDao()

    @Provides
    @Singleton
    fun provideOrderBookDao(cryptocurrencyDatabase: CryptocurrencyDataBase): OrderBookDao =
        cryptocurrencyDatabase.orderBookDao()
}