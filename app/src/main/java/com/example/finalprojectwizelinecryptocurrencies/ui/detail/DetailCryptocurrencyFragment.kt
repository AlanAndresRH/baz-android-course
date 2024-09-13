package com.example.finalprojectwizelinecryptocurrencies.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.finalprojectwizelinecryptocurrencies.databinding.FragmentDetailCryptocurrencyBinding
import com.example.finalprojectwizelinecryptocurrencies.ui.detail.adapters.AsksBidsAdapter
import com.example.finalprojectwizelinecryptocurrencies.ui.detail.viewModel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCryptocurrencyFragment : Fragment() {
    private lateinit var binding: FragmentDetailCryptocurrencyBinding

    private val detailViewModel by viewModels<DetailViewModel>()
    private val askAdapter: AsksBidsAdapter by lazy { AsksBidsAdapter() }
    private val bidsAdapter: AsksBidsAdapter by lazy { AsksBidsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailCryptocurrencyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.getDetail()
        detailViewModel.getOrderBook()

        binding.toolbarDetail.setNavigationOnClickListener { findNavController().popBackStack() }

        binding.rvAsks.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = askAdapter
        }
        binding.rvBids.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = bidsAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailViewModel.state.collect { uiState ->
                    binding.apply {
                        Glide.with(requireContext())
                            .load(uiState.book?.image)
                            .centerCrop()
                            .into(imgCryptoCurrencies)

                        tvNameCryptocurrency.text = uiState.book?.nameCrypto
                        tvNameBook.text = uiState.book?.book
                        tvHigh.text = uiState.book?.high
                        tvVolume.text = uiState.book?.volume
                        tvCreatedAt.text = uiState.book?.createdAt

                        containerLoading.isVisible = uiState.isLoading
                        containerDataDetail.isVisible = uiState.showData
                        lytError.container.isVisible = uiState.showErrorData

                        askAdapter.submitList(uiState.orderBook?.asks)
                        bidsAdapter.submitList(uiState.orderBook?.bids)

                        lytError.btnRetry.setOnClickListener {
                            detailViewModel.getDetail()
                            detailViewModel.getOrderBook()
                        }
                    }
                }
            }
        }
    }
}
