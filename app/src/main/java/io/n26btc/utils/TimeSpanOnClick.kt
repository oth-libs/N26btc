package io.n26btc.utils

import io.n26btc.domain.model.BitcoinChartTimeSpan

interface TimeSpanOnClick {
  fun onClick(bitcoinChartTimeSpan: BitcoinChartTimeSpan)
}