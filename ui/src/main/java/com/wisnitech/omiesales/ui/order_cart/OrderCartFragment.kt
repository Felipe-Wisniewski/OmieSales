package com.wisnitech.omiesales.ui.order_cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.ui.R
import com.wisnitech.omiesales.ui.databinding.FragmentOrderCartBinding
import com.wisnitech.omiesales.ui.utils.observeEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderCartFragment : Fragment() {

    private lateinit var binding: FragmentOrderCartBinding
    private val viewModel by viewModel<OrderCartViewModel>()

    private val orderCartAdapter = OrderCartAdapter(::itemOnClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val customerId = it.getLong(CUSTOMER_ID)
            viewModel.setCustomerId(customerId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderCartBinding.inflate(layoutInflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@OrderCartFragment.viewModel
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
        initObserver()
    }

    private fun initView() {
        binding.rvOrderCart.adapter = orderCartAdapter
    }

    private fun initListeners() {
        binding.fabConfirmOrder.setOnClickListener {
            setAddDiscountDialog()
        }
    }

    private fun initObserver() {
        viewModel.orderItems.observe(viewLifecycleOwner) {
            orderCartAdapter.submitList(it)
        }

        viewModel.orderPlaced.observeEvent(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.homeFragment, false)
        }
    }

    private fun setConfirmDialog() {
        val message =
            "Close the order ${viewModel.orderItems.value?.size.let { "with $it items" }}?"

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Confirm order")
            .setMessage(message)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Confirm") { dialog, _ ->
                viewModel.setNewSale()
                dialog.dismiss()
            }
            .show()
    }

    private fun itemOnClick(orderItem: OrderItem) {
        setUpdateItemDialog(orderItem)
    }

    private fun setUpdateItemDialog(item: OrderItem) {
        var quantity = item.quantity

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(item.productName)
            .setView(R.layout.item_quantity_dialog_view)
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Ok") { dialog, _ ->
                viewModel.updateOrderItem(item, quantity)
                dialog.dismiss()
            }
            .show()

        val textView = dialog.findViewById<AppCompatTextView>(R.id.txt_item_quantity)
        textView?.text = "$quantity"

        dialog.findViewById<Button>(R.id.btn_remove_item)?.setOnClickListener {
            if (quantity > 0) quantity--
            textView?.text = "$quantity"
        }

        dialog.findViewById<Button>(R.id.btn_add_item)?.setOnClickListener {
            quantity++
            textView?.text = "$quantity"
        }
    }

    private fun setAddDiscountDialog() {
        var discount = 0.0
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Add discount ?")
            .setView(R.layout.item_input_discount)
            .setNegativeButton("Cancel") { dialog, _ ->
                setConfirmDialog()
                dialog.dismiss()
            }
            .setPositiveButton("Ok") { dialog, _ ->
                viewModel.setDiscount(discount)
                dialog.dismiss()
            }
            .show()

        dialog.findViewById<TextInputEditText>(R.id.input_discount)?.doAfterTextChanged {
            val disc = it.toString()
            discount = disc.toDouble()
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.updateCart()
    }

    companion object {
        private const val CUSTOMER_ID = "customer_id"

        fun newInstance(customerId: Long) = OrderCartFragment().apply {
            arguments = Bundle().apply {
                putLong(CUSTOMER_ID, customerId)
            }
        }
    }
}