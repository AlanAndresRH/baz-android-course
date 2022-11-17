package com.example.finalprojectwizelinecryptocurrencies.domain.useCase

import com.example.finalprojectwizelinecryptocurrencies.domain.model.OrderBook
import com.example.finalprojectwizelinecryptocurrencies.domain.repositories.CryptocurrencyRepository
import javax.inject.Inject

class GetOrderBookUserCase @Inject constructor(
    private var repository: CryptocurrencyRepository
) {
    suspend operator fun invoke(book: String): Result<OrderBook?> {
        return repository.getOrderBook(book)
    }
}
