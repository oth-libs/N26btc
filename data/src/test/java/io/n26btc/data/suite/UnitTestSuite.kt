package io.n26btc.data.suite

import io.n26btc.data.mapper.BitcoinChartDataMapperTest
import io.n26btc.data.repository.BitcoinChartsRepositoryTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
  BitcoinChartsRepositoryTest::class,
  BitcoinChartDataMapperTest::class
)
internal class UnitTestSuite