package com.wisnitech.omiesales.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.ui.databinding.ItemSaleViewBinding

class HomeSalesAdapter(
    private val itemOnClick: (sale: SumSales) -> Unit
) : ListAdapter<SumSales, HomeSalesAdapter.OrdersViewHolder>(ORDER_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSaleViewBinding.inflate(layoutInflater, parent, false)
        return OrdersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class OrdersViewHolder(
        private val binding: ItemSaleViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SumSales) {
            binding.apply {
                sumSale = item
                containerItemSaleView.setOnClickListener { itemOnClick(item) }
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