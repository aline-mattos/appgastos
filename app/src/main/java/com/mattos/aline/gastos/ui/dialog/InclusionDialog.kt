package com.mattos.aline.gastos.ui.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.mattos.aline.gastos.R
import com.mattos.aline.gastos.app.App
import com.mattos.aline.gastos.enums.Category
import com.mattos.aline.gastos.extensions.strings
import com.mattos.aline.gastos.extensions.then
import com.mattos.aline.gastos.extensions.watcher
import com.mattos.aline.gastos.utils.layout.BaseDialog
import kotlinx.android.synthetic.main.dialog_inclusao.*
import java.text.SimpleDateFormat
import java.util.*

class InclusionDialog(context: Context) : BaseDialog(context) {
    //Variables
    lateinit var spinnerAdapter: ArrayAdapter<String>
    var selectedDate: Date? = null

    //Lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_inclusao)
        setCancelable(false)
        setup()
    }

    //Setup methods
    private fun setup() {
        setupFields()
        setupRecyclers()
        setupButtons()
        setupSpinner()

        validate(category = null)
    }

    private fun setupFields() {
        //mesmo código (addTextChangedListener vs DanielExtensions_Watcher), mas o segundo é menor)

        field_value.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               if(tv_value_error.visibility == View.VISIBLE) {
                   tv_value_error.visibility = View.GONE
                   tv_value_error.text = ""
               }
            }
        })

        field_date.watcher(
            onChange = {
                if(tv_date_error.visibility == View.VISIBLE) {
                    tv_date_error.visibility = View.GONE
                    tv_date_error.text = ""
                }
            }
        )

        field_date.setOnClickListener {
            DatePickerDialog(
                context,
                action = { date ->
                    selectedDate = date
                    field_date.setText(App.instance.dateFormatter.format(date))
                }
            ).show()
        }
    }

    private fun setupRecyclers() {


    }

    private fun setupButtons() {
        button_close_form.setOnClickListener {
            dismiss()
        }

        button_close_market_items.setOnClickListener {
            if (layout_market_items.visibility != View.GONE) {
                layout_market_items.visibility = View.GONE
            }
        }

        button_include_item.setOnClickListener {
            if (layout_market_items.visibility != View.VISIBLE) {
                layout_market_items.visibility = View.VISIBLE
            }
        }

        button_save.setOnClickListener {
            if(validate(views = listOf(field_value, field_date))){
                val a = String.format("%.2f", field_value.text.toString().toFloat())
                Toast.makeText(context, a, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "existe erro nos campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupSpinner() {
        spinnerAdapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            mutableListOf<String>().let {
                it.addAll(Category.getStringList())
                it.add(0, "Selecione uma categoria")
                it
            })
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                clear()

                if (position != 0) {
                    validate(category = Category.values().get(position - 1))
                } else {
                    validate(category = null)
                }
            }
        }
        spinner.adapter = spinnerAdapter
    }

    //Other methods

    private fun clear() {
        selectedDate = null

        field_value.setText("")
        field_date.setText("")
    }

    private fun validate(category: Category?) {
        when (category) {
            Category.AGUA, Category.ALUGUEL, Category.LUZ -> {
                layout_field_value.visibility = View.VISIBLE
                layout_field_date.visibility = View.VISIBLE
                button_include_item.visibility = View.GONE
                recycler_saved_items.visibility = View.GONE
                layout_total_value.visibility = View.GONE
                button_save.visibility = View.VISIBLE
            }
            Category.MERCADO -> {
                layout_field_value.visibility = View.GONE
                layout_field_date.visibility = View.VISIBLE
                button_include_item.visibility = View.VISIBLE
                recycler_saved_items.visibility = View.VISIBLE
                layout_total_value.visibility = View.VISIBLE
                button_save.visibility = View.VISIBLE
            }
            else -> {
                layout_field_value.visibility = View.GONE
                layout_field_date.visibility = View.GONE
                button_include_item.visibility = View.GONE
                recycler_saved_items.visibility = View.GONE
                layout_total_value.visibility = View.GONE
                button_save.visibility = View.GONE
            }
        }
        layout_market_items.visibility = View.GONE
    }

    private fun validate(views: List<View> ): Boolean {
        var validated = true

        views.forEach { view ->
            var message = ""
            var error = false

            when (view) {
                //VALUE VALIDATION RULES
                field_value -> {
                    val data = field_value.text.toString()
                    if (!error && data.isEmpty()) {
                        message = context.strings[R.string.error_empty_field]
                        error = true
                    }

                    if(!error && (data.toDouble() <= 0)) {
                        message = context.strings[R.string.error_value_not_positive]
                        error = true
                    }

                    tv_value_error.text = message
                    tv_value_error.visibility = message.isNotEmpty() then View.VISIBLE ?: View.GONE

                    if(validated && error) {
                        validated = false
                    }
                }

                //DATE VALIDATION RULES
                field_date -> {
                    if (!error && field_date.text.isEmpty()) {
                        message = context.strings[R.string.error_empty_field]
                        error = true
                    }

                    if(!error && selectedDate!! > Date()) {
                        message = context.strings[R.string.error_future_date]
                        error = true
                    }

                    tv_date_error.text = message
                    tv_date_error.visibility = message.isNotEmpty() then View.VISIBLE ?: View.GONE

                    if(validated && error) {
                        validated = false
                    }
                }
            }
        }

        return validated
    }

}