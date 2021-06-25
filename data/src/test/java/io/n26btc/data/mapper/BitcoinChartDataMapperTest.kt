package io.n26btc.data.mapper

import io.n26btc.data.model.BitcoinChartRetrofit
import io.n26btc.data.model.BitcoinChartRetrofitValue
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartValue
import org.junit.Test
import kotlin.test.assertEquals

class BitcoinChartDataMapperTest {

  private val mapper: Mapper<BitcoinChartRetrofit, BitcoinChartModel> = BitcoinChartDataMapper()

  @Test
  fun testMapper() {
    val expected = BitcoinChartModel("BTC", listOf(BitcoinChartValue(x = 1, y = 123.0)))

    val result = mapper.map(BitcoinChartRetrofit("BTC", listOf(BitcoinChartRetrofitValue(x = 1, y = 123.0))))

    assertEquals(expected, result)
  }

}