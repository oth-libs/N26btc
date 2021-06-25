package io.n26btc.homepage

import io.n26btc.BaseFragment
import io.n26btc.R
import io.n26btc.databinding.FragmentHomepageBinding
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

    binding.viewPager.adapter = chartPagerAdapter
  }
}