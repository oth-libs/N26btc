package io.n26btc.data.repository

import io.n26btc.data.api.BitcoinChartService
import io.n26btc.data.datasource.RemoteDataSource.getResult
import io.n26btc.data.datasource.resultFlow
import io.n26btc.data.mapper.Mapper
import io.n26btc.data.model.BitcoinChartRetrofit
import io.n26btc.domain.datasource.DataSourceResultHolder
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartRequest
import io.n26btc.domain.repository.BitcoinChartsRepository
import kotlinx.coroutines.flow.Flow

internal class BitcoinChartsRepositoryImpl(
  private val bitcoinChartService: BitcoinChartService,
  private val mapperBitcoinChartRetrofitToModel: Mapper<BitcoinChartRetrofit, BitcoinChartModel>
) : BitcoinChartsRepository {

  override fun getBitcoinChart(bitcoinChartRequest: BitcoinChartRequest): Flow<DataSourceResultHolder<BitcoinChartModel>> {
    return resultFlow(
      networkCall = {
        getResult(
          call = {
            bitcoinChartService.getBitcoinChart(
              bitcoinChartRequest.bitcoinChartType.value,
              bitcoinChartRequest.bitcoinChartTimeSpan.value
            )
          },
          transform = { mapperBitcoinChartRetrofitToModel.map(it) }
        )
      }
    )
  }

}