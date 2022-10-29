package com.example.finalprojectwizelinecryptocurrencies.dominian.useCase

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.BookDetail
import com.example.finalprojectwizelinecryptocurrencies.dominian.repositories.CryptocurrencyRepository
import javax.inject.Inject

class GetDetailBookUseCase @Inject constructor(
    private val repository: CryptocurrencyRepository
) {
    suspend operator fun invoke(book: String): Result<BookDetail> {
        return repository.getDetail(book)
    }
}