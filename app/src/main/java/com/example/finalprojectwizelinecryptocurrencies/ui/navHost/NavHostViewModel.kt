package com.example.finalprojectwizelinecryptocurrencies.ui.navHost

import androidx.lifecycle.ViewModel
import com.example.finalprojectwizelinecryptocurrencies.data.network.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.dropWhile
import javax.inject.Inject

@HiltViewModel
class NavHostViewModel @Inject constructor(
    networkMonitor: NetworkMonitor
) : ViewModel() {

    val isOnline = networkMonitor.isOnline.dropWhile { it }.distinctUntilChanged()
}
