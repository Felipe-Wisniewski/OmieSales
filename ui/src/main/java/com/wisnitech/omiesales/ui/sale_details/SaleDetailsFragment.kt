package com.wisnitech.omiesales.ui.sale_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wisnitech.omiesales.ui.databinding.FragmentSaleDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaleDetailsFragment : Fragment() {

    private lateinit var binding: FragmentSaleDetailsBinding
    private val args by navArgs<SaleDetailsFragmentArgs>()
    private val viewModel by viewModel<SaleDetailsViewModel>()

    private val itemsAdapter = SaleDetailsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaleDetailsBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@SaleDetailsFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
        initObservers()

        viewModel.setSumSales(args.sales)
        viewModel.getProductListBySaleId(args.sales.saleId)
    }

    private fun initView() {
        binding.rvSaleItems.adapter = itemsAdapter
    }

    private fun initListeners() {
        binding.toolbarSaleDetails.setOnClickListener { findNavController().navigateUp() }
    }

    private fun initObservers() {
        viewModel.saleItems.observe(viewLifecycleOwner) {
            itemsAdapter.submitList(it)
        }
    }
}