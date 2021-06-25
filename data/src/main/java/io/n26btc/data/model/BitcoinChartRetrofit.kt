package io.n26btc.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BitcoinChartRetrofit(
  @SerialName("unit") val unit: String?,
  @SerialName("values") val values: List<BitcoinChartRetrofitValue>?
)

@Serializable
internal data class BitcoinChartRetrofitValue(
  @SerialName("x") val x: Int?,
  @SerialName("y") val y: Double?
)








