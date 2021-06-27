package io.n26btc.homepage.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.n26btc.domain.model.BitcoinChartType
import io.n26btc.homepage.ChartFragment
import io.n26btc.utils.Constants.CHART_FRAGMENT_ARG_CHART_TYPE

class ChartPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

  override fun getItemCount() = BitcoinChartType.values().size

  override fun createFragment(position: Int): Fragment {
    val fragment = ChartFragment()
    fragment.arguments = Bundle().apply {
      putString(CHART_FRAGMENT_ARG_CHART_TYPE, BitcoinChartType.values()[position].name) //todo
    }
    return fragment
  }
}
