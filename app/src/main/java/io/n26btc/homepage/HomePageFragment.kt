package io.n26btc.homepage

import android.os.Bundle
import android.view.View
import io.n26btc.BaseFragment
import io.n26btc.R
import io.n26btc.databinding.FragmentHomepageBinding
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartTimeSpan
import io.n26btc.presentation.HomePageViewModel
import io.n26btc.utils.LineChartUtil.setupLineChart
import io.n26btc.utils.LineChartUtil.updateChartData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class HomePageFragment : BaseFragment<FragmentHomepageBinding>(
  layoutId = R.layout.fragment_homepage,
) {

  override val viewModel: HomePageViewModel by viewModel()

  override fun setupBinding() {
    binding.viewModel = viewModel

    binding.circulatingSupplyTimeSpanOnClick = object : TimeSpanOnClick {
      override fun onClick(bitcoinChartTimeSpan: BitcoinChartTimeSpan) = run { viewModel.updateCirculatingSupplyChart(bitcoinChartTimeSpan) }
    }

    binding.marketPriceTimeSpanOnClick = object : TimeSpanOnClick {
      override fun onClick(bitcoinChartTimeSpan: BitcoinChartTimeSpan) = run { viewModel.updateMarketPriceChart(bitcoinChartTimeSpan) }
    }

    binding.marketCapTimeSpanOnClick = object : TimeSpanOnClick {
      override fun onClick(bitcoinChartTimeSpan: BitcoinChartTimeSpan) = run { viewModel.updateMarketCapChart(bitcoinChartTimeSpan) }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupViews()

    observeViewModelCalls()
  }

  private fun setupViews() {
    binding.chartCirculatingSupply.setupLineChart()
    binding.chartCirculatingSupplyView.chart.setupLineChart()
    binding.chartMarketPriceView.chart.setupLineChart()
  }

  private fun observeViewModelCalls() {
    viewModel.circulatingSupplyData.observe(viewLifecycleOwner, ::onNewCirculatingSupplyData)
    viewModel.marketPriceData.observe(viewLifecycleOwner, ::onNewMarketPriceData)
    viewModel.marketCapData.observe(viewLifecycleOwner, ::onNewMarketCapData)
  }

  private fun onNewCirculatingSupplyData(bitcoinChartModel: BitcoinChartModel) {
    binding.chartCirculatingSupply.updateChartData(bitcoinChartModel)
    binding.chartCirculatingSupplyView.chart.updateChartData(bitcoinChartModel)
  }

  private fun onNewMarketPriceData(bitcoinChartModel: BitcoinChartModel) = run { binding.chartMarketPriceView.chart.updateChartData(bitcoinChartModel) }

  private fun onNewMarketCapData(bitcoinChartModel: BitcoinChartModel) = run { binding.chartMarketCapView.chart.updateChartData(bitcoinChartModel) }
}