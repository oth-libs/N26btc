package io.n26btc.domain.model

data class BitcoinChartRequest(
  val bitcoinChartType: BitcoinChartType,
  var bitcoinChartTimeSpan: BitcoinChartTimeSpan = BitcoinChartTimeSpan.DAYS30,
)

enum class BitcoinChartType(val value: String) {
  CIRCULATING_SUPPLY("total-bitcoins"),
  MARKET_PRICE("market-price"),
  MARKET_CAP("market-cap")
}

enum class BitcoinChartTimeSpan(val value: String) {
  DAYS30("30days"),
  DAYS60("60days"),
  YEARS1("1year")
}