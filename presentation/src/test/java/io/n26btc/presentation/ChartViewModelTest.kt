package io.n26btc.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.n26btc.domain.datasource.DataSourceResultHolder
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartRequest
import io.n26btc.domain.model.BitcoinChartTimeSpan
import io.n26btc.domain.model.BitcoinChartType
import io.n26btc.domain.model.BitcoinChartValue
import io.n26btc.domain.usecase.GetBitcoinChartUseCase
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ChartViewModelTest {

  @get:Rule
  val rule = InstantTaskExecutorRule()

  @get:Rule
  val testCoroutineRule = TestCoroutineRule()

  private val lifecycle = LifecycleRegistry(mockk())

  private val getBitcoinChartUseCase = mockk<GetBitcoinChartUseCase>()

  private val request = BitcoinChartRequest(BitcoinChartType.MARKET_PRICE)
  private val viewModel = ChartViewModel(request.bitcoinChartType.name, getBitcoinChartUseCase)

  @Before
  fun setup() {
    every { getBitcoinChartUseCase(any()) } returns flowOf(DataSourceResultHolder.success(BitcoinChartModel(currency = "BTC", values = listOf(BitcoinChartValue(x = 1, y = 123.0)))))
  }

  @Test
  fun `When lifecycle#onStart, then verify chartApiStatus is success and chartData size is 1 `() {
    lifecycle.addObserver(viewModel)

    lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)

    verify(exactly = 1) { getBitcoinChartUseCase(request) }

    assertEquals(viewModel.chartApiStatus.getOrAwaitValue(), DataSourceResultHolder.Status.SUCCESS)

    assertNotNull(viewModel.chartData.getOrAwaitValue().values)
    assertEquals(viewModel.chartData.getOrAwaitValue().values!!.size, 1)
  }

  @Test
  fun `When viewModel#getBitcoinChart is called, then verify bitcoinChartRequest is set to the new time span`() {
    viewModel.getBitcoinChart(BitcoinChartTimeSpan.YEARS1)

    assertEquals(viewModel.bitcoinChartRequest.bitcoinChartTimeSpan, BitcoinChartTimeSpan.YEARS1)
  }

  @Test
  fun `When viewModel#getBitcoinChart is called, then verify chartCurrentTimeSpan value is set`() {
    viewModel.getBitcoinChart(BitcoinChartTimeSpan.YEARS1)

    assertEquals(viewModel.chartCurrentTimeSpan.getOrAwaitValue(), BitcoinChartTimeSpan.YEARS1)
  }

  @Test
  fun `When viewModel#getBitcoinChart is called, then verify usecase is called`() {
    viewModel.getBitcoinChart(BitcoinChartTimeSpan.YEARS1)

    request.bitcoinChartTimeSpan = BitcoinChartTimeSpan.YEARS1

    verify(exactly = 1) { getBitcoinChartUseCase(request) }
  }

}