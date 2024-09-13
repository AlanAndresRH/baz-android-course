package com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.repositories

import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.local.BookLocalDataSource
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.BookRemoteDataSource
import com.example.finalprojectwizelinecryptocurrencies.data.cryptocurrencies.dataSource.remote.dto.*
import com.example.finalprojectwizelinecryptocurrencies.data.network.NetworkMonitor
import com.example.finalprojectwizelinecryptocurrencies.domain.model.*
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CryptocurrencyRepositoryImplTest {
    private lateinit var repositoryImpl: CryptocurrencyRepositoryImpl

    @RelaxedMockK
    private lateinit var bookRemoteDataSource: BookRemoteDataSource

    @RelaxedMockK
    private lateinit var bookLocalDataSource: BookLocalDataSource

    @RelaxedMockK
    private lateinit var networkMonitor: NetworkMonitor

    private val bookFake = "btc_mxn"
    private val bookListFake = BooksDto(
        listOf(
            PayloadDto(
                "btc_mxn",
                "40000",
                "20000000",
                "0.00000030000",
                "3000",
                "10.00",
                "200000000",
                "10",
                "tradingview"
            )
        ),
        true
    )
    private val bookDetailFake = BookDetailDto(
        PayloadDetailDto(
            "327640",
            "327620",
            "btc_mxn",
            "4780",
            "2022-11-18T01:25:53+00:00",
            "329860",
            "327640",
            "318630",
            "174.81805025",
            "323005.3702215784",
        ),
        true
    )
    private val asks = listOf(
        AskBidDto(
            "0.35057571",
            "btc_mxn",
            "327670"
        )
    )
    private val bids = listOf(
        AskBidDto(
            "0.35057571 ",
            "btc_mxn",
            "327670"
        )
    )
    private val orderBookFake = OrderBookDto(
        PayloadOrderDto(
            asks,
            bids,
            "2022-11-18T03:06:51+00:00",
            "2198145028",
        ),
        true
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repositoryImpl =
            CryptocurrencyRepositoryImpl(
                bookRemoteDataSource,
                bookLocalDataSource,
                networkMonitor,
                Dispatchers.Unconfined,
                Schedulers.io()
            )
    }

    @Test
    fun getBooks_when_networkIsAvailable_return_remoteData() = runTest {
        // Given
        coEvery { networkMonitor.isOnline() } returns true
        coEvery { bookRemoteDataSource.getBooks() } returns bookListFake

        // When
        val result = repositoryImpl.getBooks()

        // Then
        assertThat(result).isEqualTo(Result.success(bookListFake.toListBooks()))
    }

    @Test
    fun getBooks_when_networkIsNotAvailable_return_localData() = runTest {
        // Given
        coEvery { networkMonitor.isOnline() } returns false
        coEvery { bookLocalDataSource.getBook() } returns bookListFake.toListBooks()
            .map { it.toBookEntity() }

        // When
        val result = repositoryImpl.getBooks()

        // Then
        assertThat(result).isEqualTo(Result.success(bookListFake.toListBooks()))
    }

    @Test
    fun getBooks_networkIsNotAvailable_insert_dataInRoom() = runTest {
        // Given
        coEvery { networkMonitor.isOnline() } returns true
        val bookListEntity = bookListFake.toListBooks().map { it.toBookEntity() }
        coEvery { bookRemoteDataSource.getBooks() } returns bookListFake

        // When
        repositoryImpl.getBooks()

        // Then
        coVerify { bookLocalDataSource.insertBookEntity(bookListEntity) }
    }

    @Test
    fun getDetail_when_networkIsAvailable_return_remoteData() = runTest {
        // Given
        coEvery { networkMonitor.isOnline() } returns true
        coEvery { bookRemoteDataSource.getDetailBook(bookFake) } returns bookDetailFake

        // When
        val result = repositoryImpl.getDetail(bookFake)

        // Then
        assertThat(result).isEqualTo(Result.success(bookDetailFake.toBookDetail()))
    }

    @Test
    fun getDetail_when_networkIsNotAvailable_return_localData() = runTest {
        // Given
        coEvery { networkMonitor.isOnline() } returns false
        coEvery { bookLocalDataSource.getBookDetail(bookFake) } returns bookDetailFake.toBookDetail()
            .toBookDetailEntity()

        // When
        val result = repositoryImpl.getDetail(bookFake)

        // Then
        assertThat(result).isEqualTo(Result.success(bookDetailFake.toBookDetail()))
    }

    @Test
    fun getDetail_networkIsNotAvailable_insert_dataInRoom() = runTest {
        // Given
        coEvery { networkMonitor.isOnline() } returns true
        val bookListEntity = bookDetailFake.toBookDetail().toBookDetailEntity()
        coEvery { bookRemoteDataSource.getDetailBook(bookFake) } returns bookDetailFake

        // When
        repositoryImpl.getDetail(bookFake)

        // Then
        coVerify { bookLocalDataSource.insertBookDetail(bookListEntity) }
    }

    @Test
    fun getOrderBook_when_networkIsAvailable_return_remoteData() = runTest {
        // Given
        coEvery { networkMonitor.isOnline() } returns true
        coEvery { bookRemoteDataSource.getOrderBook(bookFake) } returns orderBookFake

        // When
        val result = repositoryImpl.getOrderBook(bookFake)

        // Then
        assertThat(result).isEqualTo(Result.success(orderBookFake.toListOrderBook()))
    }

    @Test
    fun getOrderBook_when_networkIsNotAvailable_return_localData() = runTest {
        // Given
        coEvery { networkMonitor.isOnline() } returns false
        coEvery { bookLocalDataSource.getOrderBook(bookFake) } returns orderBookFake.toListOrderBook()
            .toOrderBookEntity(bookFake)
        coEvery { bookLocalDataSource.getAsksBook(bookFake) } returns orderBookFake.toListOrderBook().asks.map { it.toAsksBookEntity() }
        coEvery { bookLocalDataSource.getBidsBook(bookFake) } returns orderBookFake.toListOrderBook().bids.map { it.toBidsBookEntity() }
        // When
        val result = repositoryImpl.getOrderBook(bookFake)

        // Then
        assertThat(result).isEqualTo(Result.success(orderBookFake.toListOrderBook()))
    }

    @Test
    fun getOrderBook_networkIsNotAvailable_insert_dataInRoom() = runTest {
        // Given
        val orderBookEntity = orderBookFake.toListOrderBook().toOrderBookEntity(bookFake)
        coEvery { networkMonitor.isOnline() } returns true
        coEvery { bookRemoteDataSource.getOrderBook(bookFake) } returns orderBookFake

        // When
        repositoryImpl.getOrderBook(bookFake)

        // Then
        coVerify {
            bookLocalDataSource.updateOrderBookEntity(
                orderBookEntity,
                orderBookFake.toListOrderBook().asks.map { it.toAsksBookEntity() },
                orderBookFake.toListOrderBook().bids.map { it.toBidsBookEntity() }
            )
        }
    }
}
