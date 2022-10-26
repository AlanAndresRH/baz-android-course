package com.example.finalprojectwizelinecryptocurrencies.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprojectwizelinecryptocurrencies.databinding.ActivityMainBinding
import com.example.finalprojectwizelinecryptocurrencies.ui.home.adapter.CryptocurrencyAdapter
import com.example.finalprojectwizelinecryptocurrencies.ui.home.viewModel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeCryptocurrencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val homeViewModel by viewModels<HomeViewModel>()
    private val adtHome: CryptocurrencyAdapter by lazy {
        CryptocurrencyAdapter {
            Snackbar.make(binding.root, "${it.book}", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        binding.rvCryptocurrency.apply {
            hasFixedSize()
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adtHome
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                homeViewModel.state.collect { uiState ->
                    binding.progressBarHome.isVisible = uiState.isLoading

                    adtHome.submitList(uiState.books)
                }

                /*homeViewModel.stateBook.collect {
                    binding.progressBarHome.isVisible = it is ResultApi.Loading
                    when (it) {
                        is ResultApi.Success -> adtHome.submitList(it.data)

                        is ResultApi.Error -> Snackbar.make(
                            binding.root,
                            "${it.message}",
                            Snackbar.LENGTH_SHORT
                        ).show()

                        else -> {}
                    }
                }*/
            }
        }
    }
}