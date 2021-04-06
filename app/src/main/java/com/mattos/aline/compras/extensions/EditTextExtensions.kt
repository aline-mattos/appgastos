package com.mattos.aline.compras.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.watcher(beforeChange: (String) -> Unit = {}, onChange: (String) -> Unit = {}, afterChange: (String) -> Unit = {}) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(string: CharSequence?, p1: Int, p2: Int, p3: Int) {
            beforeChange.invoke(this@watcher.text.toString())
        }
        override fun onTextChanged(string: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onChange.invoke(this@watcher.text.toString())
        }
        override fun afterTextChanged(editable: Editable?) {
            beforeChange.invoke(this@watcher.text.toString())
        }
    })
}