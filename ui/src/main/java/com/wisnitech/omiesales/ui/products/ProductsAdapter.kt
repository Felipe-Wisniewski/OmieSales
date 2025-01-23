package com.wisnitech.omiesales.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wisnitech.omiesales.data.model.Product
import com.wisnitech.omiesales.ui.databinding.ItemProductViewBinding

class ProductsAdapter : ListAdapter<Product, ProductsAdapter.ProductViewHolder>(PRODUCT_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductViewBinding.inflate(layoutInflater)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        product?.let { holder.bind(it) }
    }

    inner class ProductViewHolder(
        private val binding: ItemProductViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                this.product = product
            }
        }
    }

    companion object {
        private val PRODUCT_DIFF = object : DiffUtil.ItemCallback<Product>() {
            override fun areItemsTheSame(oldItem: Product, newItem: Product) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
        }
    }
}