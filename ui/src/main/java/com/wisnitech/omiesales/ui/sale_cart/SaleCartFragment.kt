package com.wisnitech.omiesales.ui.sale_cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.ui.databinding.FragmentSaleCartBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaleCartFragment : Fragment() {

    private lateinit var binding: FragmentSaleCartBinding
    private val viewModel by viewModel<SaleCartViewModel>()

    private val saleCartAdapter = SaleCartAdapter(::itemOnClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaleCartBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@SaleCartFragment.viewModel
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserver()
    }

    private fun initView() {
        binding.rvSaleCart.adapter = saleCartAdapter
    }

    private fun initObserver() {
        viewModel.orderItems.observe(viewLifecycleOwner) {
            saleCartAdapter.submitList(it)
        }
    }

    private fun itemOnClick(orderItem: OrderItem) {
        Log.d("FLMWG","item:${orderItem.productName}")
    }
}