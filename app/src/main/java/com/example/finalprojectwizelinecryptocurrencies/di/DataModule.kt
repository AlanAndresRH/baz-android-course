package com.example.finalprojectwizelinecryptocurrencies.di

import com.example.finalprojectwizelinecryptocurrencies.data.network.connectivityDataSource.ConnectivityManagerNetworkMonitor
import com.example.finalprojectwizelinecryptocurrencies.data.network.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor
}