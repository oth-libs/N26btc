package io.n26btc.utils

import io.n26btc.domain.model.BitcoinChartTimeSpan

fun interface TimeSpanOnClick {
  fun onClick(bitcoinChartTimeSpan: BitcoinChartTimeSpan)
}