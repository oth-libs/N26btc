package io.n26btc

import android.app.Application
import io.n26btc.presentation.di.PresentationModule
import io.n26btc.data.di.DataModule
import io.n26btc.domain.di.DomainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("UNUSED")
class N26btcApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@N26btcApplication)
    }

    loadModules()
  }

  private fun loadModules() {
    DomainModule.load()
    DataModule.load()
    PresentationModule.load()
  }
}
