package com.example.finalprojectwizelinecryptocurrencies.utils

import java.text.DecimalFormat
import java.text.NumberFormat

fun String?.formatCurrency(): String {
    val formatter: NumberFormat= DecimalFormat("###,###,##0.00")

    return "$ ${formatter.format(this?.toDouble()) ?: "0.0"} MXN."
}