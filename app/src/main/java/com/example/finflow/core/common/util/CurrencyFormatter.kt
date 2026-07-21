package com.example.finflow.core.common.util

import java.text.NumberFormat
import java.util.Locale

object CurrencyFormatter {

    fun format(amount: Long): String {
        return NumberFormat.getNumberInstance(Locale("fa")).format(amount) + " تومان "
    }


}