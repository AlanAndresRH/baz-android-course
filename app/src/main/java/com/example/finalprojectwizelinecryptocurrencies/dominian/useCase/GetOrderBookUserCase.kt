package com.example.finalprojectwizelinecryptocurrencies.dominian.useCase

import com.example.finalprojectwizelinecryptocurrencies.dominian.model.OrderBook
import com.example.finalprojectwizelinecryptocurrencies.dominian.repositories.CryptocurrencyRepository
import javax.inject.Inject

class GetOrderBookUserCase @Inject constructor(
    private var repository: CryptocurrencyRepository
) {
    suspend operator fun invoke(book: String): Result<OrderBook?> {
        return repository.getOrderBook(book)
    }
}