package io.n26btc.domain.model

data class BitcoinChartModel(
  val currency: String?,
  val values: List<BitcoinChartValue>?
)

data class BitcoinChartValue(
  val x: Int?,
  val y: Double?
)