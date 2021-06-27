package io.n26btc

import io.n26btc.utils.Utils
import org.junit.Test
import kotlin.test.assertEquals

class UtilsTest {

  @Test
  fun testFormatAmountWithCurrency() {
    var expected = "11,00k BTC"
    var result = Utils.formatAmountWithCurrency(11000.0, "BTC")
    assertEquals(expected, result)

    expected = "11,10k BTC"
    result = Utils.formatAmountWithCurrency(11100.0, "BTC")
    assertEquals(expected, result)

    expected = "11,10M BTC"
    result = Utils.formatAmountWithCurrency(11100000.0, "BTC")
    assertEquals(expected, result)

    expected = "111 BTC"
    result = Utils.formatAmountWithCurrency(111.0, "BTC")
    assertEquals(expected, result)
  }
}
