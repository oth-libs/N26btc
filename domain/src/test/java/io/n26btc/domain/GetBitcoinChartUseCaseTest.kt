package io.n26btc.domain

import io.mockk.coEvery
import io.mockk.mockk
import io.n26btc.domain.datasource.DataSourceResultHolder
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartRequest
import io.n26btc.domain.model.BitcoinChartType
import io.n26btc.domain.repository.BitcoinChartsRepository
import io.n26btc.domain.usecase.GetBitcoinChartUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class GetBitcoinChartUseCaseTest {
  private val repository: BitcoinChartsRepository = mockk()

  @Test
  fun testObserveStockUpdatesUseCase() = runBlocking {
    val expected = DataSourceResultHolder.success(BitcoinChartModel(currency = "BTC", values = listOf()))
    coEvery { repository.getBitcoinChart(any()) } returns flowOf(expected)

    val useCase = GetBitcoinChartUseCase(repository = repository)
    val result = useCase(BitcoinChartRequest(BitcoinChartType.MARKET_PRICE)).first()

    assertEquals(expected, result)
  }
}
