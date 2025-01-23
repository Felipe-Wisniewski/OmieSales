package com.wisnitech.omiesales.ui.sale

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wisnitech.omiesales.ui.databinding.FragmentSaleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaleFragment : Fragment() {

    private lateinit var binding: FragmentSaleBinding
    private val args by navArgs<SaleFragmentArgs>()
    private val viewModel by viewModel<SaleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setCustomerName(args.customerName)
        viewModel.setNewSale(args.customerId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaleBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@SaleFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.toolbarSale.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.fabAddProduct.setOnClickListener {
            navigateToProducts()
        }

        binding.fabConfirmSale.setOnClickListener {
            viewModel.addProductToSale()
        }
    }

    private fun initObservers() {
        viewModel.saleId.observe(viewLifecycleOwner) {
            Log.d("FLMWG", "SALE ID: $it")
        }

        viewModel.saleFinalized.observe(viewLifecycleOwner) {
            Log.d("FLMWG", "SALE FINALIZED")
            findNavController().navigateUp()
        }
    }

    private fun navigateToProducts() {
        findNavController().navigate(
            SaleFragmentDirections.actionSaleFragmentToProductsFragment(
                viewModel.saleId.value ?: 0
            )
        )
    }

    override fun onResume() {
        super.onResume()


    }
}