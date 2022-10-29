package com.example.finalprojectwizelinecryptocurrencies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.finalprojectwizelinecryptocurrencies.databinding.ActivityDetailCryptocurrencyBinding
import com.example.finalprojectwizelinecryptocurrencies.ui.detail.viewModel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCryptocurrencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCryptocurrencyBinding

    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCryptocurrencyBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        val book = intent.getStringExtra("book") ?: ""
        detailViewModel.getDetail(book)

        binding.toolbarDetail.setNavigationOnClickListener { finish() }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {

                detailViewModel.state.collect() { uiState ->
                    binding.apply {
                        Glide.with(applicationContext)
                            .load(uiState.book.image)
                            .centerCrop()
                            .into(imgCryptoCurrencies)

                        progressBar.isVisible = uiState.isLoading
                        tvNameCryptocurrency.text = uiState.book.nameCrypto
                        tvNameBook.text = uiState.book.book
                        tvHigh.text = uiState.book.high
                        tvVolume.text = uiState.book.volume
                        tvCreatedAt.text = uiState.book.createdAt
                    }
                }
            }
        }
    }
}