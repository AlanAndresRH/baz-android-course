package com.example.finalprojectwizelinecryptocurrencies.dominian.useCase

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book
import com.example.finalprojectwizelinecryptocurrencies.dominian.repositories.CryptocurrencyRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: CryptocurrencyRepository
) {

    suspend operator fun invoke(): Result<List<Book>> {
        return repository.getBooks()
    }
}