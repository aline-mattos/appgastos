package com.mattos.aline.gastos.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mattos.aline.gastos.R
import com.mattos.aline.gastos.model.MarketItem
import kotlinx.android.synthetic.main.market_include_item.view.*

class MarketItemAdapter(private val action: (MarketItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items : MutableList<MarketItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MarketItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.market_include_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        require(holder is MarketItemViewHolder)
        holder.apply {
            setup(itemMarket = items[position])

            itemView.setOnClickListener {
                action.invoke(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    //HELPER METHODS

    fun add(list: List<MarketItem>, clear:Boolean) {
        val currentSize = items.size

        items.apply {
            if (clear) {
                clear()
                addAll(list)
            }
            notifyItemRangeInserted(currentSize, list.size)
        }
    }


}