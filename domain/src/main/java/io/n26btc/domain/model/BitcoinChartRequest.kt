package io.n26btc.domain.model

sealed class BitcoinChartType(var bitcoinChartTimeSpan: BitcoinChartTimeSpan, val name: String)

class BitcoinChartTypeCirculatingSupply(bitcoinChartTimeSpan: BitcoinChartTimeSpan = BitcoinChartTimeSpan.DAYS30) : BitcoinChartType(bitcoinChartTimeSpan, "total-bitcoins")
class BitcoinChartTypeMarketPrice(bitcoinChartTimeSpan: BitcoinChartTimeSpan = BitcoinChartTimeSpan.DAYS30) : BitcoinChartType(bitcoinChartTimeSpan, "market-price")
class BitcoinChartTypeMarketCap(bitcoinChartTimeSpan: BitcoinChartTimeSpan = BitcoinChartTimeSpan.DAYS30) : BitcoinChartType(bitcoinChartTimeSpan, "market-cap")

enum class BitcoinChartTimeSpan(val value: String) {
  DAYS30("30days"),
  DAYS60("60days"),
  YEARS1("1year")
}