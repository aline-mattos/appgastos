package com.mattos.aline.gastos.ui.dialog

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.mattos.aline.gastos.R
import com.mattos.aline.gastos.extensions.strings
import com.mattos.aline.gastos.utils.layout.BaseDialog
import kotlinx.android.synthetic.main.dialog_date_picker.*

import java.util.*

class DatePickerDialog(context: Context, val action: (Date) -> Unit) : BaseDialog(context) {

    var selectedDate: Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_date_picker)
        setup()
    }

    private fun setup() {
        setupCalendar()
        setupButtons()
    }

    private fun setupCalendar() {
        calendar.setOnDateChangeListener { view, year, month, day ->
            val calendar = Calendar.getInstance().let {
                it.set(Calendar.YEAR, year)
                it.set(Calendar.MONTH, month)
                it.set(Calendar.DAY_OF_MONTH, day)
                it
            }

            selectedDate = calendar.time
        }
    }

    private fun setupButtons() {
        button_cancel.setOnClickListener {
            dismiss()
        }

        button_ok.setOnClickListener {
            action.invoke(selectedDate)
            dismiss()
        }
    }

}