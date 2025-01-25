package com.wisnitech.omiesales.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.ui.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<HomeViewModel>()

    private val ordersAdapter = HomeOrdersAdapter(::itemOnClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@HomeFragment.viewModel
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
        binding.rvSalesHome.adapter = ordersAdapter
    }

    private fun initListeners() {
        binding.fabAddSale.setOnClickListener { navigateToRegisterCustomer() }
    }

    private fun initObservers() {
        viewModel.sales.observe(viewLifecycleOwner) {
            ordersAdapter.submitList(it)
        }
    }

    private fun itemOnClick(sales: SumSales) {

    }

    private fun navigateToSaleDetails() {

    }

    private fun navigateToRegisterCustomer() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRegisterCustomerFragment())
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllOrders()
    }
}