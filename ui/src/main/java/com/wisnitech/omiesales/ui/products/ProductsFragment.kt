package com.wisnitech.omiesales.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.ui.databinding.FragmentProductsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding
    private val viewModel by viewModel<ProductsViewModel>()

    private val productsAdapter = ProductsAdapter(::itemOnClick)

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
        initObservers()
    }

    private fun initView() {
        binding.rvProductsList.adapter = productsAdapter
    }

    private fun initObservers() {
        viewModel.products.observe(viewLifecycleOwner) {
            productsAdapter.submitList(it)
        }
    }

    private fun itemOnClick(product: Product) {
        setAddProductDialog(product)
    }

    private fun setAddProductDialog(product: Product) {
        Toast.makeText(requireContext(), "Product: ${product.name}", Toast.LENGTH_SHORT).show()
        viewModel.setOrderItem(product, 1)
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllProducts()
    }
}