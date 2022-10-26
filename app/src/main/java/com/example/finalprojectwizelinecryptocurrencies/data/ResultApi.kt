package com.example.finalprojectwizelinecryptocurrencies.data

sealed class ResultApi<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : ResultApi<T>(data)

    class Success<T>(data: T?) : ResultApi<T>(data)

    class Error<T>(message: String?, data: T? = null) : ResultApi<T>(data, message)
}