package com.wisnitech.omiesales.ui.customer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.wisnitech.omiesales.ui.databinding.FragmentRegisterCustomerBinding
import com.wisnitech.omiesales.ui.utils.observeEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterCustomerFragment : Fragment() {

    private lateinit var binding: FragmentRegisterCustomerBinding
    private val viewModel by viewModel<RegisterCustomerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterCustomerBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@RegisterCustomerFragment.viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.toolbarRegisterCustomer.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.edtCustomerName.doAfterTextChanged {
            viewModel.setCustomerName(it.toString())
        }

        binding.tilCustomerPhone.setEndIconOnClickListener {
            viewModel.getCustomerByPhone()
        }

        binding.edtCustomerPhone.doAfterTextChanged {
            viewModel.setCustomerPhone(it.toString())
        }

        binding.switchRegistered.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setIsRegistered(isChecked)
        }

        binding.fabConfirmRegister.setOnClickListener {
            viewModel.setCustomer()
        }
    }

    private fun initObservers() {
        viewModel.customerId.observeEvent(viewLifecycleOwner) {
            navigateToSale(it)
        }
    }

    private fun navigateToSale(customerId: Long) {
        findNavController().navigate(
            RegisterCustomerFragmentDirections.actionRegisterCustomerFragmentToSaleFragment(
                customerId,
                viewModel.customerName.value ?: ""
            )
        )
    }
}