package com.example.finalprojectwizelinecryptocurrencies.domain.useCase

import com.example.finalprojectwizelinecryptocurrencies.domain.model.BookDetail
import com.example.finalprojectwizelinecryptocurrencies.domain.repositories.CryptocurrencyRepository
import javax.inject.Inject

class GetDetailBookUseCase @Inject constructor(
    private val repository: CryptocurrencyRepository
) {
    suspend operator fun invoke(book: String): Result<BookDetail> {
        return repository.getDetail(book)
    }
}
