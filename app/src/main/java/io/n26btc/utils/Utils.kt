package io.n26btc.utils

import java.text.DecimalFormat

object Utils {

  fun formatAmountWithCurrency(amount: Double, currency: String?) = "${prettyCount(amount)} $currency"

  private fun prettyCount(number: Number): String {
    val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
    val numValue = number.toLong()
    val value = Math.floor(Math.log10(numValue.toDouble())).toInt()
    val base = value / 3
    return if (value >= 3 && base < suffix.size) {
      DecimalFormat("#0.00").format(numValue / Math.pow(10.0, (base * 3).toDouble())) + suffix[base]
    } else {
      DecimalFormat("#,##0").format(numValue)
    }
  }

}