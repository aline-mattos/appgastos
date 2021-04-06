package com.mattos.aline.gastos.ui.activity

import android.app.Activity
import android.os.Bundle
import com.mattos.aline.gastos.R
import com.mattos.aline.gastos.ui.dialog.InclusionDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity(){


    //Life Cycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    //Setup
    private fun setup() {
        setupButton()
    }

    private fun setupButton() {
        botao_inclusao.setOnClickListener {
            InclusionDialog(it.context).show()
        }
    }

}