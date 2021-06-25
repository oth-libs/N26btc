package io.n26btc.homepage

import android.os.Bundle
import android.view.View
import io.n26btc.BaseFragment
import io.n26btc.R
import io.n26btc.databinding.FragmentChartBinding
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartTimeSpan
import io.n26btc.presentation.ChartViewModel
import io.n26btc.utils.LineChartUtil.setupLineChart
import io.n26btc.utils.LineChartUtil.updateChartData
import io.n26btc.utils.TimeSpanOnClick
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChartFragment : BaseFragment<FragmentChartBinding>(
  layoutId = R.layout.fragment_chart,
) {

  override val viewModel: ChartViewModel by viewModel { parametersOf(arguments?.getString("blaa")) }

  override fun setupBinding() {
    binding.viewModel = viewModel

    binding.timeSpanOnClick = object : TimeSpanOnClick {
      override fun onClick(bitcoinChartTimeSpan: BitcoinChartTimeSpan) = run { viewModel.loadCirculatingSupplyChart(bitcoinChartTimeSpan) }
    }
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupViews()

    observeViewModelCalls()
  }

  private fun setupViews() {
    binding.chartCirculatingSupplyView.chart.setupLineChart()
  }

  private fun observeViewModelCalls() {
    viewModel.chartData.observe(viewLifecycleOwner, ::onNewCirculatingSupplyData)
  }

  private fun onNewCirculatingSupplyData(bitcoinChartModel: BitcoinChartModel) = run { binding.chartCirculatingSupplyView.chart.updateChartData(bitcoinChartModel) }
}