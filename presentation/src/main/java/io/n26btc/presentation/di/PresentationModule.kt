package io.n26btc.presentation.di

import io.n26btc.presentation.ChartViewModel
import io.n26btc.presentation.HomePageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object PresentationModule {

  fun load() {
    loadKoinModules(module {
      viewModel { HomePageViewModel() }
      viewModel { (chartType: String) -> ChartViewModel(chartType = chartType, getBitcoinChartUseCase = get()) }
    })
  }
}