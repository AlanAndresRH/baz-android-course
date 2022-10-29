package com.example.finalprojectwizelinecryptocurrencies.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprojectwizelinecryptocurrencies.R
import com.example.finalprojectwizelinecryptocurrencies.databinding.ActivityMainBinding
import com.example.finalprojectwizelinecryptocurrencies.ui.detail.DetailCryptocurrencyActivity
import com.example.finalprojectwizelinecryptocurrencies.ui.home.adapter.CryptocurrencyAdapter
import com.example.finalprojectwizelinecryptocurrencies.ui.home.viewModel.HomeViewModel
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeCryptocurrencyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val homeViewModel by viewModels<HomeViewModel>()
    private val adtHome: CryptocurrencyAdapter by lazy {
        CryptocurrencyAdapter {
            val intent = Intent(applicationContext, DetailCryptocurrencyActivity::class.java)
            intent.putExtra("book", it.book)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarHome)

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

                    if (!uiState.errorMsg.isNullOrEmpty())
                        Snackbar.make(binding.root, "${uiState.errorMsg}", Snackbar.LENGTH_SHORT)
                            .show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home_sort_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_list_mxn -> {
                homeViewModel.changeFilterKey(KeyFilter.FILTER_MXN)
                true
            }

            R.id.filter_list_all -> {
                homeViewModel.changeFilterKey(KeyFilter.NO_FILTER)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}