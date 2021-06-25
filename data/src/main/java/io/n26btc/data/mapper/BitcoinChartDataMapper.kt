package io.n26btc.data.mapper

import io.n26btc.data.model.BitcoinChartRetrofit
import io.n26btc.data.model.BitcoinChartRetrofitValue
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartValue

/**
 * Map [BitcoinChartRetrofit] to [BitcoinChartModel]
 */
internal class BitcoinChartDataMapper : Mapper<BitcoinChartRetrofit, BitcoinChartModel> {

  override fun map(from: BitcoinChartRetrofit): BitcoinChartModel {
    return from.run {
      BitcoinChartModel(
        currency = unit,
        values = mapValues(values)
      )
    }
  }

  private fun mapValues(values: List<BitcoinChartRetrofitValue>?): List<BitcoinChartValue> {
    return mutableListOf<BitcoinChartValue>().apply {
      values?.run {
        forEach { item ->

          val bitcoinChartValue = BitcoinChartValue(
            x = item.x,
            y = item.y
          )

          add(bitcoinChartValue)
        }
      }
    }
  }
}