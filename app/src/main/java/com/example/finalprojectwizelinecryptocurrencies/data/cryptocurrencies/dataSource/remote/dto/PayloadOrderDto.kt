package com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.dto

data class PayloadOrderDto(
    val asks: List<AskBidDto>? = null,
    val bids: List<AskBidDto>? = null,
    val sequence: String? = null,
    val updated_at: String? = null
)