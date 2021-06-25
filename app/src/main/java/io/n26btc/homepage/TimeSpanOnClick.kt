package io.n26btc.homepage

import io.n26btc.domain.model.BitcoinChartTimeSpan

interface TimeSpanOnClick {
  fun onClick(bitcoinChartTimeSpan: BitcoinChartTimeSpan)
}