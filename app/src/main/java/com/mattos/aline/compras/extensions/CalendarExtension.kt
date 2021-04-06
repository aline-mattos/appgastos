package com.mattos.aline.compras.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formatToBRDate() : String{
    val formatDate = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatDate)
    return format.format(this.time)
}