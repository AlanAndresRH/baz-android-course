package com.example.finalprojectwizelinecryptocurrencies.domain.useCase

import com.example.finalprojectwizelinecryptocurrencies.domain.model.Book
import com.example.finalprojectwizelinecryptocurrencies.domain.repositories.CryptocurrencyRepository
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter
import io.reactivex.Single
import javax.inject.Inject

class GetBooksFilterUseCase @Inject constructor(
    private var repository: CryptocurrencyRepository
) {
    suspend operator fun invoke(key: KeyFilter): Result<List<Book>> {
        return repository.getBooks().map { books ->
            when (key) {
                KeyFilter.FILTER_MXN -> {
                    books.filter { crypto ->
                        crypto.book.lowercase().contains("mxn")
                    }
                }

                KeyFilter.NO_FILTER -> books
            }
        }
    }

    fun invokeRxJava(key: KeyFilter): Single<List<Book>> {
        return repository.getBooksRxJava().map { books ->
            when (key) {
                KeyFilter.FILTER_MXN -> {
                    books.filter { crypto ->
                        crypto.book.lowercase().contains("mxn")
                    }
                }

                KeyFilter.NO_FILTER -> books
            }
        }
    }
}
