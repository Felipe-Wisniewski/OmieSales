package com.wisnitech.omiesales.ui.sale_cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wisnitech.omiesales.ui.databinding.FragmentSaleCartBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class SaleCartFragment : Fragment() {

    private lateinit var binding: FragmentSaleCartBinding
    private val viewModel by viewModel<SaleCartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaleCartBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObserver()
    }

    private fun initView() {

    }

    private fun initObserver() {
        viewModel.orderItems.observe(viewLifecycleOwner) {

        }
    }
}