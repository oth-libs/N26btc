package io.n26btc.suite

import io.n26btc.ButtonsSelectedStateTest
import io.n26btc.ErrorViewOnStartTest
import io.n26btc.NoInternetTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
  ButtonsSelectedStateTest::class,
  ErrorViewOnStartTest::class,
  NoInternetTest::class
)
internal class AndroidTestSuite