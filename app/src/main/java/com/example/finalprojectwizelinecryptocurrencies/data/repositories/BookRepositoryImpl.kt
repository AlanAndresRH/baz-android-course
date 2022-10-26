package com.example.finalprojectwizelinecryptocurrencies.data.repositories

import com.example.finalprojectwizelinecryptocurrencies.data.ResultApi
import com.example.finalprojectwizelinecryptocurrencies.data.source.CryptocurrencyApi
import com.example.finalprojectwizelinecryptocurrencies.data.source.remote.dto.toListBooks
import com.example.finalprojectwizelinecryptocurrencies.dominian.model.Book
import com.example.finalprojectwizelinecryptocurrencies.dominian.repositories.CryptocurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: CryptocurrencyApi
) : CryptocurrencyRepository {

    override fun getBooks(): Flow<ResultApi<List<Book>>> = flow {
        emit(ResultApi.Loading())

        try {
            val response = api.getBooks().toListBooks()
            emit(ResultApi.Success(response))
        } catch (e: HttpException) {
            emit(
                ResultApi.Error(
                    message = "Oops! Algo salió mal",
                    data = null
                )
            )
        } catch (e: IOException) {
            emit(
                ResultApi.Error(
                    message = "No se pudo llegar al servidor, verifique su conexión a Internet",
                    data = null
                )
            )
        }
    }

    override suspend fun getBooksNew(): Result<List<Book>> = withContext(Dispatchers.IO) {
        kotlin.runCatching {
            api.getBooks().toListBooks()
        }
    }
}