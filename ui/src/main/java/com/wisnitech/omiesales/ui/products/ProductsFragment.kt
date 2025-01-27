package com.wisnitech.omiesales.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.ui.R
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

        viewModel.getAllProducts()
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
        var quantity = 1

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(product.name)
            .setView(R.layout.item_quantity_dialog_view)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Ok") { dialog, _ ->
                viewModel.setOrderItem(product, quantity)
                dialog.dismiss()
            }
            .show()

        val textView = dialog.findViewById<AppCompatTextView>(R.id.txt_item_quantity)

        dialog.findViewById<Button>(R.id.btn_remove_item)?.setOnClickListener {
            if (quantity > 1) quantity--
            textView?.text = "$quantity"
        }

        dialog.findViewById<Button>(R.id.btn_add_item)?.setOnClickListener {
            quantity++
            textView?.text = "$quantity"
        }
    }
}