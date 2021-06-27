package io.n26btc

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import io.n26btc.domain.model.BitcoinChartType
import io.n26btc.homepage.ChartFragment
import io.n26btc.utils.Constants
import io.n26btc.utils.Constants.CHART_FRAGMENT_ARG_CHART_TYPE
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NoInternetTest {

  @Before
  fun disableNetworks() {
    getInstrumentation().uiAutomation.executeShellCommand("svc wifi disable")
    getInstrumentation().uiAutomation.executeShellCommand("svc data disable")
  }

  /**
   * testing is on emulator - on personal devices data might want to be disabled
   */
  @After
  fun enableNetworks() {
    getInstrumentation().uiAutomation.executeShellCommand("svc wifi enable")
    getInstrumentation().uiAutomation.executeShellCommand("svc data enable")
  }

  @Test
  fun testWhenNoInternetThenShowNoInternetError() {
    val fragmentArgs = bundleOf(CHART_FRAGMENT_ARG_CHART_TYPE to BitcoinChartType.MARKET_PRICE.name)
    val scenario = launchFragmentInContainer<ChartFragment>(fragmentArgs)

    // wait for networks to be disabled, then start the test
    Thread.sleep(500)

    onView(withId(R.id.btn60days)).perform(click())

    onView(withId(R.id.viewChartNoInternet)).check(matches(isDisplayed()))
  }
}
