package com.mattos.aline.compras.utils.layout

import android.app.Dialog
import android.content.Context
import com.mattos.aline.compras.app.App

abstract class BaseDialog(context: Context) : Dialog(context, theme){
    val mContext = context

    companion object {
        val theme: Int = com.mattos.aline.compras.R.style.DialogTheme
        val app: App = App.instance
    }
}