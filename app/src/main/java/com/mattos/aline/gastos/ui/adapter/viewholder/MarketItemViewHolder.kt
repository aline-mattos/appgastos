package com.mattos.aline.gastos.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mattos.aline.gastos.R
import com.mattos.aline.gastos.model.MarketItem

class MarketItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var valueView: TextView = itemView.findViewById(R.id.tv_item_value)
    var nameView: TextView = itemView.findViewById(R.id.tv_item_name)

    //SETUP METHODS
    fun setup(itemMarket: MarketItem) {
        valueView.text = itemMarket.value.toString()
        nameView.text = itemMarket.name
    }
}