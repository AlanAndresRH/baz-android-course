package com.example.finalprojectwizelinecryptocurrencies.domain.useCase

import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.repositories.CryptocurrencyRepositoryImpl
import com.example.finalprojectwizelinecryptocurrencies.domain.model.Book
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetBooksFilterUseCaseTest {
    private lateinit var getBooksFilterRxJavaUseCase: GetBooksFilterRxJavaUseCase

    @RelaxedMockK
    private lateinit var repository: CryptocurrencyRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getBooksFilterRxJavaUseCase = GetBooksFilterRxJavaUseCase(repository)
    }

    @Test
    fun invokeRxJava() = runTest {
        // Given
        val fakeData = listOf(
            Book(
                "btc_mxn",
                "40000",
                0,
                "0.00000030000",
                "3000"
            )
        )
        coEvery { repository.getBooksRxJava() } returns Single.just(fakeData)

        // When
        val result = getBooksFilterRxJavaUseCase.invokeRxJava(KeyFilter.FILTER_MXN)

        // Then
        assertThat(result.blockingGet()).isEqualTo(fakeData)
    }
}
