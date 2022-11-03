package com.example.finalprojectwizelinecryptocurrencies.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.finalprojectwizelinecryptocurrencies.databinding.ActivityDetailCryptocurrencyBinding
import com.example.finalprojectwizelinecryptocurrencies.ui.detail.adapters.AsksBidsAdapter
import com.example.finalprojectwizelinecryptocurrencies.ui.detail.viewModel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCryptocurrencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCryptocurrencyBinding

    private val detailViewModel by viewModels<DetailViewModel>()
    private val askAdapter: AsksBidsAdapter by lazy { AsksBidsAdapter() }
    private val bidsAdapter: AsksBidsAdapter by lazy { AsksBidsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCryptocurrencyBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        val book = intent.getStringExtra("book") ?: ""
        detailViewModel.getDetail(book)
        detailViewModel.getOrderBook(book)

        binding.toolbarDetail.setNavigationOnClickListener { finish() }
        binding.rvAsks.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = askAdapter
        }
        binding.rvBids.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = bidsAdapter
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {

                detailViewModel.state.collect() { uiState ->
                    binding.apply {
                        Glide.with(applicationContext)
                            .load(uiState.book.image)
                            .centerCrop()
                            .into(imgCryptoCurrencies)

                        askAdapter.submitList(uiState.orderBook.asks)
                        bidsAdapter.submitList(uiState.orderBook.bids)

                        tvNameCryptocurrency.text = uiState.book.nameCrypto
                        tvNameBook.text = uiState.book.book
                        tvHigh.text = uiState.book.high
                        tvVolume.text = uiState.book.volume
                        tvCreatedAt.text = uiState.book.createdAt
                        containerProgress.isVisible = uiState.isLoading
                    }
                }
            }
        }
    }
}