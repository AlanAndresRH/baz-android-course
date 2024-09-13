package com.example.finalprojectwizelinecryptocurrencies.di

import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.repositories.CryptocurrencyRepositoryImpl
import com.example.finalprojectwizelinecryptocurrencies.domain.repositories.CryptocurrencyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {
    @Binds
    abstract fun bindCryptocurrencyRepository(impl: CryptocurrencyRepositoryImpl): CryptocurrencyRepository
}
