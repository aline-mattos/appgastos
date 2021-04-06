package com.mattos.aline.compras.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class MarketItem(
    @SerializedName("id") var id: Long? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("quantity") var quantity: Double? = null,
    @SerializedName("value") var value: Double? = null
)