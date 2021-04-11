package com.mattos.aline.gastos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mattos.aline.gastos.R
import com.mattos.aline.gastos.model.MarketItem
import com.mattos.aline.gastos.ui.adapter.viewholder.MarketItemSavedViewHolder

class MarketItemSavedAdapter(private val action: () -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: MutableList<MarketItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MarketItemSavedViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.market_list_item_saved, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        require(holder is MarketItemSavedViewHolder)

        holder.apply {
            setup(itemMarket = items[position], last = position == items.size - 1, action = {
                this@MarketItemSavedAdapter.action.invoke()
                notifyItemChanged(position)
            })
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    //HELPER METHODS
    fun add(item: MarketItem, clear: Boolean) {
        items.apply {
            if (clear) clear()
            add(item)
            notifyDataSetChanged()
        }
    }
}








