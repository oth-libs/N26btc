package io.n26btc.presentation

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import io.n26btc.domain.datasource.DataSourceResultHolder
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartTimeSpan
import io.n26btc.domain.model.BitcoinChartTypeCirculatingSupply
import io.n26btc.domain.model.BitcoinChartTypeMarketCap
import io.n26btc.domain.model.BitcoinChartTypeMarketPrice
import io.n26btc.domain.usecase.GetBitcoinChartUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomePageViewModel(
  private val getBitcoinChartUseCase: GetBitcoinChartUseCase,
  application: Application
) : BaseViewModel(application) {

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // __      ___                 _      _           _____        _
  // \ \    / (_)               | |    (_)         |  __ \      | |
  //  \ \  / / _  _____      __ | |     ___   _____| |  | | __ _| |_ __ _
  //   \ \/ / | |/ _ \ \ /\ / / | |    | \ \ / / _ \ |  | |/ _` | __/ _` |
  //    \  /  | |  __/\ V  V /  | |____| |\ V /  __/ |__| | (_| | || (_| |
  //     \/   |_|\___| \_/\_/   |______|_| \_/ \___|_____/ \__,_|\__\__,_|
  //
  //Font Name: Big
  // circulating supply
  private val _circulatingSupplyData = MutableLiveData<BitcoinChartModel>()
  val circulatingSupplyData: LiveData<BitcoinChartModel> = _circulatingSupplyData

  private val _circulatingSupplyCurrentTimeSpan = MutableLiveData<BitcoinChartTimeSpan>()
  val circulatingSupplyCurrentTimeSpan: LiveData<BitcoinChartTimeSpan> = _circulatingSupplyCurrentTimeSpan

  private val _circulatingSupplyApiStatus = MutableLiveData<DataSourceResultHolder.Status>()
  val circulatingSupplyApiStatus: LiveData<DataSourceResultHolder.Status> = _circulatingSupplyApiStatus

  // market price
  private val _marketPriceData = MutableLiveData<BitcoinChartModel>()
  val marketPriceData: LiveData<BitcoinChartModel> = _marketPriceData

  private val _marketPriceCurrentTimeSpan = MutableLiveData<BitcoinChartTimeSpan>()
  val marketPriceCurrentTimeSpan: LiveData<BitcoinChartTimeSpan> = _marketPriceCurrentTimeSpan

  private val _marketPriceApiStatus = MutableLiveData<DataSourceResultHolder.Status>()
  val marketPriceApiStatus: LiveData<DataSourceResultHolder.Status> = _marketPriceApiStatus

  // market cap
  private val _marketCapData = MutableLiveData<BitcoinChartModel>()
  val marketCapData: LiveData<BitcoinChartModel> = _marketCapData

  private val _marketCapCurrentTimeSpan = MutableLiveData<BitcoinChartTimeSpan>()
  val marketCapCurrentTimeSpan: LiveData<BitcoinChartTimeSpan> = _marketCapCurrentTimeSpan

  private val _marketCapApiStatus = MutableLiveData<DataSourceResultHolder.Status>()
  val marketCapApiStatus: LiveData<DataSourceResultHolder.Status> = _marketCapApiStatus

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  _____        _
  // |  __ \      | |
  // | |  | | __ _| |_ __ _
  // | |  | |/ _` | __/ _` |
  // | |__| | (_| | || (_| |
  // |_____/ \__,_|\__\__,_|
  //
  //Font Name: Big
  private val bitcoinChartTypeCirculatingSupply = BitcoinChartTypeCirculatingSupply()
  private val bitcoinChartTypeMarketPrice = BitcoinChartTypeMarketPrice()
  private val bitcoinChartTypeMarketCap = BitcoinChartTypeMarketCap()

  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  //  _    _           _____
  // | |  | |         / ____|
  // | |  | |___  ___| |     __ _ ___  ___
  // | |  | / __|/ _ \ |    / _` / __|/ _ \
  // | |__| \__ \  __/ |___| (_| \__ \  __/
  //  \____/|___/\___|\_____\__,_|___/\___|
  //
  //Font Name: Big
  /**
   * Start app with loading the first page
   */
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  private fun loadInitialCharts() {
    loadBitcoinCirculatingSupply()
    loadBitcoinMarketPrice()
  }

  private fun loadBitcoinCirculatingSupply() {
    _circulatingSupplyCurrentTimeSpan.value = bitcoinChartTypeCirculatingSupply.bitcoinChartTimeSpan

    viewModelScope.launch {
      getBitcoinChartUseCase(bitcoinChartTypeCirculatingSupply).collect {
        _circulatingSupplyApiStatus.value = it.status

        if (it.data != null) {
          _circulatingSupplyData.value = it.data
        }
      }
    }
  }

  private fun loadBitcoinMarketPrice() {
    _marketPriceCurrentTimeSpan.value = bitcoinChartTypeMarketPrice.bitcoinChartTimeSpan

    viewModelScope.launch {
      getBitcoinChartUseCase(bitcoinChartTypeMarketPrice).collect {
        _marketPriceApiStatus.value = it.status

        if (it.data != null) {
          _marketPriceData.value = it.data
        }
      }
    }
  }

  private fun loadBitcoinMarketCap() {
    _marketCapCurrentTimeSpan.value = bitcoinChartTypeMarketCap.bitcoinChartTimeSpan

    viewModelScope.launch {
      getBitcoinChartUseCase(bitcoinChartTypeMarketCap).collect {
        _marketCapApiStatus.value = it.status

        if (it.data != null) {
          _marketCapData.value = it.data
        }
      }
    }
  }

  fun updateCirculatingSupplyChart(bitcoinChartTimeSpan: BitcoinChartTimeSpan? = null) {
    bitcoinChartTimeSpan?.let { bitcoinChartTypeCirculatingSupply.bitcoinChartTimeSpan = bitcoinChartTimeSpan }
    loadBitcoinCirculatingSupply()
  }

  fun updateMarketPriceChart(bitcoinChartTimeSpan: BitcoinChartTimeSpan? = null) {
    bitcoinChartTimeSpan?.let { bitcoinChartTypeMarketPrice.bitcoinChartTimeSpan = bitcoinChartTimeSpan }
    loadBitcoinMarketPrice()
  }

  fun updateMarketCapChart(bitcoinChartTimeSpan: BitcoinChartTimeSpan? = null) {
    bitcoinChartTimeSpan?.let { bitcoinChartTypeMarketCap.bitcoinChartTimeSpan = bitcoinChartTimeSpan }
    loadBitcoinMarketCap()
  }

}