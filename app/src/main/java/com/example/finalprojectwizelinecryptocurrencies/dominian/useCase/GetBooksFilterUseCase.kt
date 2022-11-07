package com.example.finalprojectwizelinecryptocurrencies.dominian.useCase

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book
import com.example.finalprojectwizelinecryptocurrencies.dominian.repositories.CryptocurrencyRepository
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter
import javax.inject.Inject

class GetBooksFilterUseCase @Inject constructor(
    private var repository: CryptocurrencyRepository
){
    suspend operator fun invoke(key: KeyFilter) : Result<List<Book>>{
        val books = repository.getBooks()
        return when (key) {
            KeyFilter.FILTER_MXN -> {
                books.map { value: List<Book> ->
                    value.filter { crypto ->
                        crypto.book.lowercase().contains("mxn")
                    }
                }
            }

            KeyFilter.NO_FILTER -> books
        }
    }
}