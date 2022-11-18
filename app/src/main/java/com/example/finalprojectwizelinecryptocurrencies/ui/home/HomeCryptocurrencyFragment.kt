package com.example.finalprojectwizelinecryptocurrencies.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprojectwizelinecryptocurrencies.R
import com.example.finalprojectwizelinecryptocurrencies.databinding.FragmentHomeCryptocurrencyBinding
import com.example.finalprojectwizelinecryptocurrencies.ui.home.adapter.CryptocurrencyAdapter
import com.example.finalprojectwizelinecryptocurrencies.ui.home.viewModel.HomeViewModel
import com.example.finalprojectwizelinecryptocurrencies.utils.KeyFilter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeCryptocurrencyFragment : Fragment() {
    private var _binding: FragmentHomeCryptocurrencyBinding? = null
    private val binding: FragmentHomeCryptocurrencyBinding get() = _binding!!

    private var showMexicoFilter = false
    private var showAllCountryFilter = false

    private val homeViewModel by viewModels<HomeViewModel>()
    private val adtHome: CryptocurrencyAdapter by lazy {
        CryptocurrencyAdapter {

            findNavController().navigate(
                HomeCryptocurrencyFragmentDirections.actionHomeCryptocurrencyFragmentToDetailCryptocurrencyFragment(
                    it.book
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeCryptocurrencyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel.changeFilterKeyRxJava(KeyFilter.FILTER_MXN)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbarHome)
        setupMenu()

        binding.rvCryptocurrency.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adtHome
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.state.collect { uiState ->
                    adtHome.submitList(uiState.books)
                    showMexicoFilter = uiState.showMexico
                    showAllCountryFilter = uiState.showAllCountry

                    binding.apply {
                        if (!uiState.errorMsg.isNullOrEmpty())
                            Snackbar.make(
                                binding.root,
                                "${uiState.errorMsg}",
                                Snackbar.LENGTH_SHORT
                            )
                                .show()

                        lytError.container.isVisible = uiState.showErrorData
                        containerLoading.isVisible = uiState.isLoading
                        rvCryptocurrency.isVisible = !uiState.isLoading
                        lytError.btnRetry.setOnClickListener {
                            homeViewModel.changeFilterKeyRxJava(KeyFilter.FILTER_MXN)
                        }
                    }
                }
            }
        }
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.menu_home_sort_list, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    return when (menuItem.itemId) {
                        R.id.filter_list_mxn -> {
                            // homeViewModel.changeFilterKey(KeyFilter.FILTER_MXN)
                            homeViewModel.changeFilterKeyRxJava(KeyFilter.FILTER_MXN)
                            true
                        }

                        R.id.filter_list_all -> {
                            // homeViewModel.changeFilterKey(KeyFilter.NO_FILTER)
                            homeViewModel.changeFilterKeyRxJava(KeyFilter.NO_FILTER)
                            true
                        }

                        else -> true
                    }
                }
            },
            viewLifecycleOwner, Lifecycle.State.RESUMED
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
