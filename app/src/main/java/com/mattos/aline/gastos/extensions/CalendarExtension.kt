package com.mattos.aline.gastos.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formatToBRDate() : String{
    val formatDate = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatDate)
    return format.format(this.time)
}