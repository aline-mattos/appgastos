package com.mattos.aline.gastos.ui.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mattos.aline.gastos.R
import com.mattos.aline.gastos.extensions.then
import com.mattos.aline.gastos.model.MarketItem

class MarketItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var valueView: TextView = itemView.findViewById(R.id.tv_item_value)
    var nameView: TextView = itemView.findViewById(R.id.tv_item_name)
    var lineView: View = itemView.findViewById(R.id.view_line)

    //SETUP METHODS
    fun setup(itemMarket: MarketItem, last: Boolean) {
        valueView.setText("${itemMarket.value} €")
        nameView.text = itemMarket.name

        lineView.visibility = last then View.GONE ?: View.VISIBLE
    }
}