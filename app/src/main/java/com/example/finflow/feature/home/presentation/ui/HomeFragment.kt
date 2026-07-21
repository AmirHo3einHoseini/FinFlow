package com.example.finflow.feature.home.presentation.ui

import android.os.Bundle
import android.system.Os.bind
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.finflow.R
import com.example.finflow.core.common.util.CurrencyFormatter
import com.example.finflow.databinding.FragmentHomeBinding
import com.example.finflow.feature.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiState()
        onclickListener()
    }

    private fun onclickListener() {
        binding.fabAddTransaction.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_transaction)
        }
    }


    private fun observeUiState() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    binding.txtHeader.text = "سلام، ${uiState.userName} 👋"

                    binding.txtTotalStock.text =
                        CurrencyFormatter.format(uiState.totalBalance)

//                    if (uiState.isBalanceVisible) {
//                        binding.txtTotalStock.text = CurrencyFormatter.format(uiState.totalBalance)
//                    } else binding.txtTotalStock.text = "***********"
                }
            }
        }
    }
}