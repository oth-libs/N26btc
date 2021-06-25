package io.n26btc.data.repository

import io.mockk.coEvery
import io.mockk.mockk
import io.n26btc.data.api.BitcoinChartService
import io.n26btc.data.mapper.BitcoinChartDataMapper
import io.n26btc.data.model.BitcoinChartRetrofit
import io.n26btc.data.model.BitcoinChartRetrofitValue
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartRequest
import io.n26btc.domain.model.BitcoinChartTimeSpan
import io.n26btc.domain.model.BitcoinChartType
import io.n26btc.domain.model.BitcoinChartValue
import io.n26btc.domain.repository.BitcoinChartsRepository
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals

internal class BitcoinChartsRepositoryTest {

  private lateinit var bitcoinChartService: BitcoinChartService
  private lateinit var repository: BitcoinChartsRepository

  @Before
  fun setUp() {
    bitcoinChartService = mockk(relaxed = true)

    repository = BitcoinChartsRepositoryImpl(
      bitcoinChartService = bitcoinChartService,
      mapperBitcoinChartRetrofitToModel = BitcoinChartDataMapper()
    )
  }

  @Test
  fun testGetBitcoinChart() = runBlocking {
    val expected = BitcoinChartModel("BTC", listOf(BitcoinChartValue(x = 1, y = 123.0)))

    coEvery { bitcoinChartService.getBitcoinChart(any(), any()) } returns Response.success(BitcoinChartRetrofit("BTC", listOf(BitcoinChartRetrofitValue(x = 1, y = 123.0))))
    val result = repository.getBitcoinChart(BitcoinChartRequest(BitcoinChartType.MARKET_PRICE, BitcoinChartTimeSpan.DAYS30)).last().data

    assertEquals(expected, result)
  }
}