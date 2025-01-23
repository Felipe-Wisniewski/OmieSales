package com.wisnitech.omiesales.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wisnitech.omiesales.ui.databinding.FragmentProductsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private val args by navArgs<ProductsFragmentArgs>()
    private val viewModel by viewModel<ProductsViewModel>()

    private val productsAdapter = ProductsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.setSaleId(args.saleId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@ProductsFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
        initObservers()
    }

    private fun initView() {
        binding.rvProductsList.adapter = productsAdapter
    }

    private fun initListeners() {
        binding.toolbarProducts.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initObservers() {
        viewModel.products.observe(viewLifecycleOwner) {
            productsAdapter.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllProducts()
    }
}