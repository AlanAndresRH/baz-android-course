package com.example.finalprojectwizelinecryptocurrencies.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectwizelinecryptocurrencies.di.MainScheduler
import com.example.finalprojectwizelinecryptocurrencies.domain.useCase.GetBooksFilterRxJavaUseCase
import com.example.finalprojectwizelinecryptocurrencies.ui.home.HomeState
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @MainScheduler private val mainScheduler: Scheduler,
    //private val getBooksFilterUseCase: GetBooksFilterUseCase,
    private val getBooksFilterRxJavaUseCase: GetBooksFilterRxJavaUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val _state = MutableStateFlow(HomeState(isLoading = true))
    val state: StateFlow<HomeState> = _state

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
                            isLoading = false,
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
                        )
                    }
                }
            )
        }
    }*/

    fun changeFilterKeyRxJava(key: KeyFilter) {
        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val resBook = getBooksFilterRxJavaUseCase.invokeRxJava(key)
            resBook.observeOn(mainScheduler).subscribe({ books ->
                _state.update {
                    it.copy(
                        books = books,
                        keyFilter = key,
                        isLoading = false
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
                        errorMsg = errorMsg
                    )
                }
            }).let {
                disposable.add(it)
            }
        }
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}
