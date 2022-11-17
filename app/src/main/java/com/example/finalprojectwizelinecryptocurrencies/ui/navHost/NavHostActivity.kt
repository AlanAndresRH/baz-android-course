package com.example.finalprojectwizelinecryptocurrencies.ui.navHost

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.finalprojectwizelinecryptocurrencies.R
import com.example.finalprojectwizelinecryptocurrencies.databinding.ActivityNavHostBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavHostBinding

    private val navHostViewModel: NavHostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavHostBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        lifecycleScope.launch {
            navHostViewModel.isOnline.collect { isOnline ->
                if (isOnline)
                    setupSnackBar(getString(R.string.connection_restored))
                else
                    setupSnackBar(getString(R.string.offline))
            }
        }
    }

    private fun setupSnackBar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_SHORT).show()
    }
}
