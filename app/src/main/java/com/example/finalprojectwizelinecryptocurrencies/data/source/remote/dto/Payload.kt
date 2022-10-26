package com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto

data class Payload(
    val book: String? = null,
    val default_chart: String? = null,
    val maximum_amount: String? = null,
    val maximum_price: String? = null,
    val maximum_value: String? = null,
    val minimum_amount: String? = null,
    val minimum_price: String? = null,
    val minimum_value: String? = null,
    val tick_size: String? = null
)