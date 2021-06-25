package io.n26btc.domain.repository

import io.n26btc.domain.datasource.DataSourceResultHolder
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartType
import kotlinx.coroutines.flow.Flow

interface BitcoinChartsRepository {

  fun getBitcoinChart(bitcoinChartType: BitcoinChartType): Flow<DataSourceResultHolder<BitcoinChartModel>>
}