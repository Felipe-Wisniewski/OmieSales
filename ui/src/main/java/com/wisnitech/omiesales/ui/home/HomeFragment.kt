package com.wisnitech.omiesales.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.ui.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModel<HomeViewModel>()

    private val salesAdapter = HomeSalesAdapter(::itemOnClick)

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
        binding.rvSalesHome.adapter = salesAdapter
    }

    private fun initListeners() {
        binding.fabAddSale.setOnClickListener { navigateToRegisterCustomer() }
    }

    private fun initObservers() {
        viewModel.sales.observe(viewLifecycleOwner) {
            salesAdapter.submitList(it)
        }
    }

    private fun itemOnClick(sales: SumSales) {
        navigateToSaleDetails(sales)
    }

    private fun navigateToSaleDetails(sales: SumSales) {
        navigateTo(HomeFragmentDirections.actionHomeFragmentToSaleDetailsFragment(sales))
    }

    private fun navigateToRegisterCustomer() {
        navigateTo(HomeFragmentDirections.actionHomeFragmentToRegisterCustomerFragment())
    }

    private fun navigateTo(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllSales()
    }
}