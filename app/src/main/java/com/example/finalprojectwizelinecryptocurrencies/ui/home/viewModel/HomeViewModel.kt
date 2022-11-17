package com.example.finalprojectwizelinecryptocurrencies.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectwizelinecryptocurrencies.domain.useCase.GetBooksFilterUseCase
import com.example.finalprojectwizelinecryptocurrencies.ui.state.HomeState
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBooksFilterUseCase: GetBooksFilterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState(isLoading = true))
    val state: StateFlow<HomeState> = _state

    init {
        //changeFilterKey(KeyFilter.FILTER_MXN)
        changeFilterKeyRxJava(KeyFilter.FILTER_MXN)
    }

    /*fun changeFilterKey(key: KeyFilter) {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val resBook = getBooksFilterUseCase(key)
            resBook.fold(
                { resSuccess ->
                    _state.update {
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

                    _state.update { state ->
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
    }*/

    fun changeFilterKeyRxJava(key: KeyFilter) {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val resBook = getBooksFilterUseCase.invokeRxJava(key)
            resBook.observeOn(AndroidSchedulers.mainThread()).subscribe({ books ->
                _state.update {
                    it.copy(
                        books = books,
                        keyFilter = key,
                        showMexico = (key == KeyFilter.FILTER_MXN),
                        showAllCountry = (key == KeyFilter.NO_FILTER),
                        isLoading = false,
                        showErrorData = (books.isEmpty())
                    )
                }
            }, {
                val errorMsg = when (it) {
                    is HttpException -> "Error de conexión"

                    is IOException -> "Error en el servicio verifique conexión a internet"

                    else -> "Unknown error"
                }

                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        errorMsg = errorMsg,
                        showMexico = (key == KeyFilter.FILTER_MXN),
                        showAllCountry = (key == KeyFilter.NO_FILTER),
                        showErrorData = true
                    )
                }
            })
        }
    }
}
