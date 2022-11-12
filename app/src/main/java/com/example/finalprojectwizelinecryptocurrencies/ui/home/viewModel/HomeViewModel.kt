package com.example.finalprojectwizelinecryptocurrencies.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectwizelinecryptocurrencies.dominian.useCase.GetBooksFilterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.finalprojectwizelinecryptocurrencies.ui.state.HomeState
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBooksFilterUseCase: GetBooksFilterUseCase
) : ViewModel() {

    val state = MutableStateFlow(HomeState(isLoading = true))

    init {
        changeFilterKey(KeyFilter.FILTER_MXN)
    }

    fun changeFilterKey(key: KeyFilter) {
        state.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            val resBook = getBooksFilterUseCase(key)
            resBook.fold({ resSuccess ->
                state.update {
                    it.copy(
                        books = resSuccess,
                        keyFilter = key,
                        showMexico = (key == KeyFilter.FILTER_MXN),
                        showAllCountry = (key == KeyFilter.NO_FILTER),
                        isLoading = false,
                        showErrorData = (resSuccess.isEmpty())
                    )
                }
            },
                {
                    val errorMsg = when (it) {
                        is HttpException -> "Error de conexión"

                        is IOException -> "Error en el servicio verifique conexión a internet"

                        else -> "Unknown error"
                    }

                    state.update { state ->
                        state.copy(
                            isLoading = false,
                            errorMsg = errorMsg,
                            showMexico = (key == KeyFilter.FILTER_MXN),
                            showAllCountry = (key == KeyFilter.NO_FILTER),
                            showErrorData = true
                        )
                    }
                }
            )
        }
    }
}