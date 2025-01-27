package com.wisnitech.omiesales.ui.sale_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wisnitech.omiesales.data.model.SaleProductClient
import com.wisnitech.omiesales.ui.databinding.ItemSaleDetailsViewBinding

class SaleDetailsAdapter :
    ListAdapter<SaleProductClient, SaleDetailsAdapter.SaleDetailsViewHolder>(ITEM_DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleDetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSaleDetailsViewBinding.inflate(layoutInflater, parent, false)
        return SaleDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaleDetailsViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    inner class SaleDetailsViewHolder(
        private val binding: ItemSaleDetailsViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SaleProductClient) {
            binding.saleItem = item
        }
    }

    companion object {
        private val ITEM_DIFF = object : DiffUtil.ItemCallback<SaleProductClient>() {
            override fun areItemsTheSame(oldItem: SaleProductClient, newItem: SaleProductClient) =
                oldItem.productName == newItem.productName

            override fun areContentsTheSame(
                oldItem: SaleProductClient,
                newItem: SaleProductClient
            ) =
                oldItem == newItem
        }
    }
}