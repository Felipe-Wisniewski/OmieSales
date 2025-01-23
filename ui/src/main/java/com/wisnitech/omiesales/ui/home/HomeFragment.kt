package com.wisnitech.omiesales.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.wisnitech.omiesales.ui.databinding.FragmentHomeBinding
import com.wisnitech.omiesales.ui.di.modules
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<HomeViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)

//        loadKoinModules(modules)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.fabAddSale.setOnClickListener { navigateToSales() }
    }

    private fun initObservers() {
        viewModel.sale.observe(viewLifecycleOwner) {
            Log.d("FLMWG","sales: ${it.joinToString()}")
            Toast.makeText(requireContext(), "SALES: ${it.size}", Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateToSales() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSaleFragment())
    }

    override fun onResume() {
        super.onResume()

        viewModel.getSales()
    }
}