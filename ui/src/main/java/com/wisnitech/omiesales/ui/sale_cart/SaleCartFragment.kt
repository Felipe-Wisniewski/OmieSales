package com.wisnitech.omiesales.ui.sale_cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.ui.R
import com.wisnitech.omiesales.ui.databinding.FragmentSaleCartBinding
import com.wisnitech.omiesales.ui.utils.observeEvent
import org.koin.androidx.viewmodel.ext.android.viewModel

class SaleCartFragment : Fragment() {

    private lateinit var binding: FragmentSaleCartBinding
    private val viewModel by viewModel<SaleCartViewModel>()

    private val saleCartAdapter = SaleCartAdapter(::itemOnClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            val saleId = it.getLong(SALE_ID)
            viewModel.setSaleId(saleId)
        }
    }

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
        initListeners()
        initObserver()
    }

    private fun initView() {
        binding.rvSaleCart.adapter = saleCartAdapter
    }

    private fun initListeners() {
        binding.fabConfirmSale.setOnClickListener {
            setConfirmDialog()
        }
    }

    private fun initObserver() {
        viewModel.orderItems.observe(viewLifecycleOwner) {
            saleCartAdapter.submitList(it)
        }

        viewModel.orderPlaced.observeEvent(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.homeFragment, false)
        }
    }

    private fun setConfirmDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Confirm order")
            .setMessage("Confirm order!!")
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Confirm") { dialog, _ ->
                viewModel.placeOrder()
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
            quantity--
            textView?.text = "$quantity"
        }

        dialog.findViewById<Button>(R.id.btn_add_item)?.setOnClickListener {
            quantity++
            textView?.text = "$quantity"
        }
    }

    companion object {
        private const val SALE_ID = "sale_id"

        fun newInstance(saleId: Long) = SaleCartFragment().apply {
            arguments = Bundle().apply {
                putLong(SALE_ID, saleId)
            }
        }
    }
}