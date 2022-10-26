package com.example.finalprojectwizelinecryptocurrencies.data.source

import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.BooksDto
import retrofit2.http.GET

interface CryptocurrencyApi {

    @GET("available_books/")
    suspend fun getBooks(): BooksDto
}