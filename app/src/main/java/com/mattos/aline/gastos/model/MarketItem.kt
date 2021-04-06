package com.mattos.aline.gastos.model

import com.google.gson.annotations.SerializedName

data class MarketItem(
    @SerializedName("id") var id: Long? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("quantity") var quantity: Double? = null,
    @SerializedName("value") var value: Double? = null
)