package com.wisnitech.omiesales.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.ui.databinding.ItemOrderViewBinding

class HomeOrdersAdapter(
    private val itemOnClick: (order: SumSales) -> Unit
) : ListAdapter<SumSales, HomeOrdersAdapter.OrdersViewHolder>(ORDER_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderViewBinding.inflate(layoutInflater)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class OrdersViewHolder(
        private val binding: ItemOrderViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SumSales) {
            binding.apply {
                sale = item
                root.setOnClickListener { itemOnClick(item) }
            }
        }
    }

    companion object {
        private val ORDER_DIFF = object : DiffUtil.ItemCallback<SumSales>() {
            override fun areItemsTheSame(oldItem: SumSales, newItem: SumSales) =
                oldItem.saleId == newItem.saleId

            override fun areContentsTheSame(oldItem: SumSales, newItem: SumSales) =
                oldItem == newItem
        }
    }
}