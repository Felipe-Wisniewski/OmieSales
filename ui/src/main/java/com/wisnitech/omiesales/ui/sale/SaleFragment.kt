package com.wisnitech.omiesales.ui.sale

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator
import com.wisnitech.omiesales.ui.R
import com.wisnitech.omiesales.ui.databinding.FragmentSaleBinding
import com.wisnitech.omiesales.ui.products.ProductsFragment
import com.wisnitech.omiesales.ui.sale_cart.SaleCartFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaleFragment : Fragment() {

    private lateinit var binding: FragmentSaleBinding
    private val args by navArgs<SaleFragmentArgs>()
    private val viewModel by viewModel<SaleViewModel>()

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            setConfirmCloseOrder()
        }

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
            setConfirmCloseOrder()
        }
    }

    private fun initObservers() {
        viewModel.saleId.observe(viewLifecycleOwner) {
            setViewPager()
        }

        viewModel.saleDeleted.observe(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.homeFragment, false)
        }
    }

    private fun setConfirmCloseOrder() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage("Are you sure you want to cancel the order?")
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Yes") { dialog, _ ->
                viewModel.deleteSale()
                dialog.dismiss()
            }
            .show()
    }

    private fun setViewPager() {
        viewPager = binding.viewPagerSale.apply {
            adapter = SalePagerAdapter(this@SaleFragment)
        }

        TabLayoutMediator(binding.tabSale, viewPager) { tab, position ->
            tab.text = if (position == 0) "Order" else "Products"
        }.attach()
    }

    private inner class SalePagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return if (position == 0) {
                SaleCartFragment.newInstance(saleId = viewModel.saleId.value ?: 0L)
            } else {
                ProductsFragment()
            }
        }
    }
}