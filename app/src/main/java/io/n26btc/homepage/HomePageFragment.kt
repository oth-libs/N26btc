package io.n26btc.homepage

import com.google.android.material.tabs.TabLayoutMediator
import io.n26btc.BaseFragment
import io.n26btc.R
import io.n26btc.databinding.FragmentHomepageBinding
import io.n26btc.domain.model.BitcoinChartType
import io.n26btc.homepage.adapter.ChartPagerAdapter
import io.n26btc.presentation.HomePageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomePageFragment : BaseFragment<FragmentHomepageBinding>(
  layoutId = R.layout.fragment_homepage,
) {

  override val viewModel: HomePageViewModel by viewModel()

  private val chartPagerAdapter by lazy { ChartPagerAdapter(this) }

  override fun setupBinding() {
    binding.viewModel = viewModel

    binding.viewPager.apply {
      isUserInputEnabled = false
      adapter = chartPagerAdapter
    }

    TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
      tab.setText(
        when (BitcoinChartType.values()[position]) {
          BitcoinChartType.CIRCULATING_SUPPLY -> {
            R.string.chart_type_circulating_supply
          }

          BitcoinChartType.MARKET_PRICE -> {
            R.string.chart_type_market_price
          }

          BitcoinChartType.MARKET_CAP -> {
            R.string.chart_type_market_cap
          }
        }
      )
    }.attach()

  }
}