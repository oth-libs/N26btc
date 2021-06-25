package io.n26btc.domain.usecase

import io.n26btc.domain.datasource.DataSourceResultHolder
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartRequest
import io.n26btc.domain.repository.BitcoinChartsRepository
import kotlinx.coroutines.flow.Flow

class GetBitcoinChartUseCase(private val repository: BitcoinChartsRepository) {

  operator fun invoke(bitcoinChartRequest: BitcoinChartRequest): Flow<DataSourceResultHolder<BitcoinChartModel>> = repository.getBitcoinChart(bitcoinChartRequest)

}
