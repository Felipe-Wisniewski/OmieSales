package com.wisnitech.omiesales.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wisnitech.omiesales.data.model.SumSales
import com.wisnitech.omiesales.ui.databinding.ItemProductHeaderViewBinding
import com.wisnitech.omiesales.ui.databinding.ItemSaleViewBinding
import com.wisnitech.omiesales.ui.products.ProductsAdapter

class HomeSalesAdapter(
    private val itemOnClick: (sale: SumSales) -> Unit
) : ListAdapter<SumSales, RecyclerView.ViewHolder>(ORDER_DIFF) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == null) VIEW_SALE_HEADER else VIEW_SALE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_SALE_HEADER -> {
                val binding = ItemProductHeaderViewBinding.inflate(layoutInflater, parent, false)
                ProductsAdapter.HeaderViewHolder(binding)
            }
            VIEW_SALE_ITEM -> {

                val binding = ItemSaleViewBinding.inflate(layoutInflater, parent, false)
                OrdersViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductsAdapter.HeaderViewHolder -> {
                val date = getItem(position + 1).saleDate
                date.let { holder.bind(it) }
            }
            is OrdersViewHolder -> {
                val item = getItem(position)
                item?.let { holder.bind(it) }
            }
        }
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
        const val VIEW_SALE_HEADER = 0
        const val VIEW_SALE_ITEM = 1

        private val ORDER_DIFF = object : DiffUtil.ItemCallback<SumSales>() {
            override fun areItemsTheSame(oldItem: SumSales, newItem: SumSales) =
                oldItem.saleId == newItem.saleId

            override fun areContentsTheSame(oldItem: SumSales, newItem: SumSales) =
                oldItem == newItem
        }
    }
}