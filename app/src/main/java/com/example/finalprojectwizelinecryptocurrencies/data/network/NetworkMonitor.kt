package com.example.finalprojectwizelinecryptocurrencies.data.network

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isOnline: Flow<Boolean>

    fun isOnline(): Boolean
}
