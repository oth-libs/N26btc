package io.n26btc

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.n26btc.domain.model.BitcoinChartType
import io.n26btc.homepage.ChartFragment
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ButtonsSelectedStateTest {

  @Test
  fun testBtn30daysSelected() {
    val fragmentArgs = bundleOf("blaa" to BitcoinChartType.MARKET_PRICE.name)
    val scenario = launchFragmentInContainer<ChartFragment>(fragmentArgs)

    onView(withId(R.id.btn30days)).perform(click())

    onView(withId(R.id.btn30days)).check(matches(isSelected()))
    onView(withId(R.id.btn60days)).check(matches(not(isSelected())))
    onView(withId(R.id.btn1year)).check(matches(not(isSelected())))
  }

  @Test
  fun testBtn60daysSelected() {
    val fragmentArgs = bundleOf("blaa" to BitcoinChartType.MARKET_PRICE.name)
    val scenario = launchFragmentInContainer<ChartFragment>(fragmentArgs)

    onView(withId(R.id.btn60days)).perform(click())

    onView(withId(R.id.btn30days)).check(matches(not(isSelected())))
    onView(withId(R.id.btn60days)).check(matches(isSelected()))
    onView(withId(R.id.btn1year)).check(matches(not(isSelected())))
  }

  @Test
  fun testBtn1YearSelected() {
    val fragmentArgs = bundleOf("blaa" to BitcoinChartType.MARKET_PRICE.name)
    val scenario = launchFragmentInContainer<ChartFragment>(fragmentArgs)

    onView(withId(R.id.btn1year)).perform(click())

    onView(withId(R.id.btn30days)).check(matches(not(isSelected())))
    onView(withId(R.id.btn60days)).check(matches(not(isSelected())))
    onView(withId(R.id.btn1year)).check(matches(isSelected()))
  }
}
