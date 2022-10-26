package com.example.finalprojectwizelinecryptocurrencies.ui.home.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectwizelinecryptocurrencies.dominian.useCase.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.finalprojectwizelinecryptocurrencies.ui.state.HomeState
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    val state = MutableStateFlow(HomeState(isLoading = true))

    //val stateBook: MutableStateFlow<ResultApi<List<Book>>> = MutableStateFlow(ResultApi.Loading())

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    //val eventFlow = _eventFlow.asSharedFlow()

    init {
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch {
            /*getBooksUseCase().onEach { result ->
                stateBook.value = result

                /*when (result) {
                    is ResultApi.Success -> {
                        state.update {
                            it.copy(
                                books = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }

                    is ResultApi.Error -> {
                        _eventFlow.emit(UIEvent.ShowSnackBar(result.message ?: "Unknown Error"))
                        state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    }

                    is ResultApi.Loading -> {
                        state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                }*/
            }*/

            state.update {
                it.copy(
                    isLoading = true
                )
            }
            val response = getBooksUseCase()

            response.fold({  res ->
                state.update { state ->
                    state.copy(
                        books = res,
                        isLoading = false
                    )
                }
            }, {
                _eventFlow.emit(UIEvent.ShowSnackBar(it.message ?: "Unknown error"))

                state.update { state ->
                    state.copy(
                        isLoading = false
                    )
                }
            })
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }
}