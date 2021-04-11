package com.mattos.aline.gastos.ui.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mattos.aline.gastos.R
import com.mattos.aline.gastos.extensions.then
import com.mattos.aline.gastos.model.MarketItem
import org.w3c.dom.Text

class MarketItemSavedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var valueView: TextView = itemView.findViewById(R.id.tv_item_value)
    var nameView: TextView = itemView.findViewById(R.id.tv_item_name)
    var lineView: View = itemView.findViewById(R.id.view_line)

    var quantityView: TextView = itemView.findViewById(R.id.tv_quantity)
    var buttonPlus: ImageView = itemView.findViewById(R.id.button_plus)
    var buttonMinus: ImageView = itemView.findViewById(R.id.button_minus)

    lateinit var itemMarket: MarketItem
    var last: Boolean = false
    lateinit var action: () -> Unit

    //SETUP METHODS
    fun setup(itemMarket: MarketItem, last: Boolean, action:() -> Unit) {
        this.itemMarket = itemMarket
        this.last = last
        this.action = action

        setupButtons()
        setupData()
    }

    private fun setupData() {
        valueView.setText("${itemMarket?.value} €")
        nameView.text = itemMarket.name ?: ""
        quantityView.text = "${itemMarket.quantity.toInt()}"

        lineView.visibility = last then View.GONE ?: View.VISIBLE
    }

    private fun setupButtons() {
        buttonMinus.setOnClickListener {
            if(itemMarket.quantity > 0) {
                itemMarket.quantity = itemMarket.quantity - 1
                setupData()
                action?.invoke()
            }
        }

        buttonPlus.setOnClickListener {
            if(itemMarket.quantity < 10) {
                itemMarket.quantity = itemMarket.quantity + 1
                setupData()
                action?.invoke()
            }
        }

    }

}