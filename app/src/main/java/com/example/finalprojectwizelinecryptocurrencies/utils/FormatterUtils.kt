package com.example.finalprojectwizelinecryptocurrencies.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun String?.formatCurrency(currency: String): String {
    val formatter: NumberFormat = DecimalFormat("###,###,##0.0000")

    return "$ ${formatter.format(this?.toDouble()) ?: "0.0"} ${currency.uppercase()}."
}

fun String?.formatDate(): String {
    val dateNoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ROOT)
    val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ROOT)
    val formattedDate: Date? = dateNoFormat.parse(this ?: "0000/00/00 HH:mm:ss")

    return dateFormat.format(formattedDate ?: "0000/00/00 HH:mm:ss")
}
