package io.n26btc.domain.di

import io.n26btc.domain.usecase.GetBitcoinChartUseCase
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DomainModule {

  fun load() {
    loadKoinModules(module {
      single { GetBitcoinChartUseCase(repository = get()) }
    })
  }
}