package com.mattos.aline.gastos.enums


enum class Category(val type: String) {
    AGUA(type = "Água"),
    ALUGUEL(type = "Aluguel"),
    LUZ(type = "Luz"),
    MERCADO(type = "Mercado");

    companion object {
        fun getStringList(): List<String> {
            return values().map {
                it.type
            }
        }
    }
}