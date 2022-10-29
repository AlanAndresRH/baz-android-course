package com.example.finalprojectwizelinecryptocurrencies.ui.detail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectwizelinecryptocurrencies.dominian.useCase.GetDetailBookUseCase
import com.example.finalprojectwizelinecryptocurrencies.ui.state.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailBookUseCase: GetDetailBookUseCase
) : ViewModel() {

    val state = MutableStateFlow(DetailState(isLoading = true))

    init {
        getDetail(state.value.book.book ?: "")
    }

    fun getDetail(book: String) {
        state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            val resBook = getDetailBookUseCase(book)

            resBook.fold({ resSuccess ->
                state.update { state ->
                    state.copy(
                        book = resSuccess,
                        isLoading = false
                    )
                }
            }, {
                val errorMsg = when (it) {
                    is HttpException -> "Error de conexión"

                    is IOException -> "Error en el servicio verifique conexión a internet"

                    else -> "Unknown error"
                }

                state.update { state ->
                    state.copy(
                        isLoading = false,
                        errorMsg = errorMsg
                    )
                }
            })
        }
    }
}