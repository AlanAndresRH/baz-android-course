package com.example.finalprojectwizelinecryptocurrencies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalprojectwizelinecryptocurrencies.databinding.ActivityNavHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavHostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavHostBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
    }
}