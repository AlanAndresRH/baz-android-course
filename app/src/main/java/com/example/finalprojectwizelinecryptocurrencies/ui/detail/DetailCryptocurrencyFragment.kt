package com.example.finalprojectwizelinecryptocurrencies.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

    private val args: DetailCryptocurrencyFragmentArgs by navArgs()
    private val detailViewModel by viewModels<DetailViewModel>()
    private val askAdapter: AsksBidsAdapter by lazy { AsksBidsAdapter() }
    private val bidsAdapter: AsksBidsAdapter by lazy { AsksBidsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailCryptocurrencyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.getDetail(args.book)
        detailViewModel.getOrderBook(args.book)

        binding.toolbarDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.rvAsks.apply {
            hasFixedSize()
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = askAdapter
        }
        binding.rvBids.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = bidsAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                detailViewModel.state.collect { uiState ->
                    binding.apply {
                        Glide.with(requireContext())
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
                        containerProgressBar.container.isVisible = uiState.isLoading
                    }
                }
            }
        }
    }
}