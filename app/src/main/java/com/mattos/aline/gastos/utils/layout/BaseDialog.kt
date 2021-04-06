package com.mattos.aline.gastos.utils.layout

import android.app.Dialog
import android.content.Context
import com.mattos.aline.gastos.app.App

abstract class BaseDialog(context: Context) : Dialog(context, theme){
    val mContext = context

    companion object {
        val theme: Int = com.mattos.aline.gastos.R.style.DialogTheme
        val app: App = App.instance
    }
}