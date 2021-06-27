package io.n26btc

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.n26btc.domain.model.BitcoinChartType
import io.n26btc.homepage.ChartFragment
import io.n26btc.utils.Constants
import io.n26btc.utils.Constants.CHART_FRAGMENT_ARG_CHART_TYPE
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ErrorViewOnStartTest {

  @Test
  fun testWhenFragmentStartThenOnlyLoadingIsShown() {
    val fragmentArgs = bundleOf(CHART_FRAGMENT_ARG_CHART_TYPE to BitcoinChartType.MARKET_PRICE.name)
    val scenario = launchFragmentInContainer<ChartFragment>(fragmentArgs)

    onView(withId(R.id.viewChartNoInternet)).check(matches(not(isDisplayed())))
    onView(withId(R.id.viewChartError)).check(matches(not(isDisplayed())))
    onView(withId(R.id.viewChartLoading)).check(matches(isDisplayed()))
  }
}
