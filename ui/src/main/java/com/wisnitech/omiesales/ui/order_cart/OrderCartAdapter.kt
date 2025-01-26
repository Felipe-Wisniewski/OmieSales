package com.wisnitech.omiesales.ui.order_cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wisnitech.omiesales.data.model.OrderItem
import com.wisnitech.omiesales.ui.databinding.ItemCartViewBinding

class OrderCartAdapter(
    private val itemOnClick: (orderItem: OrderItem) -> Unit
) : ListAdapter<OrderItem, OrderCartAdapter.CartViewHolder>(ITEM_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCartViewBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class CartViewHolder(
        private val binding: ItemCartViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OrderItem) {
            binding.apply {
                orderItem = item
                root.setOnClickListener { itemOnClick(item) }
            }
        }
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<OrderItem>() {
            override fun areItemsTheSame(oldItem: OrderItem, newItem: OrderItem) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: OrderItem, newItem: OrderItem) =
                oldItem == newItem
        }
    }
}