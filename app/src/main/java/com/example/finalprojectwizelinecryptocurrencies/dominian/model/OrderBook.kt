package com.example.finalprojectwizelinecryptocurrencies.dominian.model

data class OrderBook(
    val asks: List<AsksBidsBook>? = null,
    val bids: List<AsksBidsBook>? = null,
    val sequence: String? = null,
    val updated_at: String? = null
)