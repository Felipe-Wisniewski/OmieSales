package com.wisnitech.omiesales.ui.sale

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wisnitech.omiesales.ui.databinding.FragmentSaleBinding

class SaleFragment : Fragment() {

    private lateinit var binding: FragmentSaleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaleBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.toolbarSale.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun navigateToProducts() {

    }
}