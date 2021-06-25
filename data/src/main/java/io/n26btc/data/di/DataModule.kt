package io.n26btc.data.di

import io.n26btc.data.api.BitcoinChartService
import io.n26btc.data.api.RetrofitFactory
import io.n26btc.data.mapper.BitcoinChartDataMapper
import io.n26btc.data.repository.BitcoinChartsRepositoryImpl
import io.n26btc.domain.repository.BitcoinChartsRepository
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalSerializationApi
object DataModule {

  fun load() {
    loadKoinModules(module {
      // Json
      single { Json { ignoreUnknownKeys = true } }

      //  retrofit
      single { RetrofitFactory().build(json = get()) }
      single { (get() as Retrofit).create(BitcoinChartService::class.java) }

      single<BitcoinChartsRepository> { BitcoinChartsRepositoryImpl(bitcoinChartService = get(), mapperBitcoinChartRetrofitToModel = BitcoinChartDataMapper()) }
    })
  }
}