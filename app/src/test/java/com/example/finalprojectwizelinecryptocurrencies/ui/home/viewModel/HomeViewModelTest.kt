package com.example.finalprojectwizelinecryptocurrencies.ui.home.viewModel

import com.example.finalprojectwizelinecryptocurrencies.domain.model.Book
import com.example.finalprojectwizelinecryptocurrencies.domain.useCase.GetBooksFilterRxJavaUseCase
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class HomeViewModelTest {
    @RelaxedMockK
    private lateinit var getBooksFilterRxJavaUseCase: GetBooksFilterRxJavaUseCase
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        homeViewModel = HomeViewModel(Schedulers.trampoline(), getBooksFilterRxJavaUseCase)
    }

    @Test
    fun changeFilterKeyRxJava() = runTest {
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

        coEvery { getBooksFilterRxJavaUseCase.invokeRxJava(KeyFilter.FILTER_MXN) } returns Single.just(fakeData)

        // When
        homeViewModel.changeFilterKeyRxJava(KeyFilter.FILTER_MXN)

        // Then
        assertThat(homeViewModel.state.first().books).isEqualTo(fakeData)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
