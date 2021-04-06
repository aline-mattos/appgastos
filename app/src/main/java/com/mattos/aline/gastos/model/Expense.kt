package com.mattos.aline.gastos.model

import com.google.gson.annotations.SerializedName
import com.mattos.aline.gastos.enums.Category
import java.util.*

data class Expense(
    @SerializedName("id")       var id: Long? = null,
    @SerializedName("value")    var value: Double? = null,
    @SerializedName("category") var category: Category? = null,
    @SerializedName("date")     var date: Date? = null,
    @SerializedName("date")     var marketItems: List<MarketItem>? = null
)
