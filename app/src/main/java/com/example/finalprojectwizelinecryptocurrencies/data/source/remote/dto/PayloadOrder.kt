package com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto

data class PayloadOrder(
    val asks: List<AskBidBook>? = null,
    val bids: List<AskBidBook>? = null,
    val sequence: String? = null,
    val updated_at: String? = null
)