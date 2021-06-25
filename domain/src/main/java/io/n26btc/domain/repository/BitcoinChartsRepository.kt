package io.n26btc.domain.repository

import io.n26btc.domain.datasource.DataSourceResultHolder
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartRequest
import kotlinx.coroutines.flow.Flow

interface BitcoinChartsRepository {

  fun getBitcoinChart(bitcoinChartRequest: BitcoinChartRequest): Flow<DataSourceResultHolder<BitcoinChartModel>>
}