package com.wisnitech.omiesales.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.ui.databinding.ItemProductHeaderViewBinding
import com.wisnitech.omiesales.ui.databinding.ItemProductViewBinding

class ProductsAdapter(
    private val itemOnClick: (product: Product) -> Unit
) : ListAdapter<Product, RecyclerView.ViewHolder>(PRODUCT_DIFF) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == null) VIEW_TYPE_HEADER else VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                val binding = ItemProductHeaderViewBinding.inflate(layoutInflater, parent, false)
                HeaderViewHolder(binding)
            }

            VIEW_TYPE_ITEM -> {
                val binding = ItemProductViewBinding.inflate(layoutInflater, parent, false)
                ProductViewHolder(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                val category = getItem(position + 1)?.category?.uppercase()
                category?.let { holder.bind(it) }
            }

            is ProductViewHolder -> {
                val item = getItem(position)
                item?.let { holder.bind(it) }
            }
        }

    }

    class HeaderViewHolder(
        private val binding: ItemProductHeaderViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(header: String) {
            binding.txtHeader.text = header
        }
    }

    inner class ProductViewHolder(
        private val binding: ItemProductViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Product) {
            binding.apply {
                product = item
                root.setOnClickListener { itemOnClick(item) }
            }
        }
    }

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_ITEM = 1

        private val PRODUCT_DIFF = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
        }
    }
}