package io.n26btc.presentation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import io.n26btc.domain.datasource.DataSourceResultHolder
import io.n26btc.domain.model.BitcoinChartModel
import io.n26btc.domain.model.BitcoinChartRequest
import io.n26btc.domain.model.BitcoinChartTimeSpan
import io.n26btc.domain.model.BitcoinChartType
import io.n26btc.domain.usecase.GetBitcoinChartUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ChartViewModel(
  chartType: String,
  private val getBitcoinChartUseCase: GetBitcoinChartUseCase
) : BaseViewModel() {

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
  private val _chartData = MutableLiveData<BitcoinChartModel>()
  val chartData: LiveData<BitcoinChartModel> = _chartData

  private val _chartCurrentTimeSpan = MutableLiveData<BitcoinChartTimeSpan>()
  val chartCurrentTimeSpan: LiveData<BitcoinChartTimeSpan> = _chartCurrentTimeSpan

  private val _chartApiStatus = MutableLiveData<DataSourceResultHolder.Status>()
  val chartApiStatus: LiveData<DataSourceResultHolder.Status> = _chartApiStatus

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
  private val bitcoinChartRequest = BitcoinChartRequest(BitcoinChartType.valueOf(chartType))

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

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  private fun loadInitialChart() {
    getBitcoinChart()
  }

  /**
   * get chart values according to the current [bitcoinChartRequest]
   */
  private fun getBitcoinChart() {
    _chartCurrentTimeSpan.value = bitcoinChartRequest.bitcoinChartTimeSpan

    viewModelScope.launch {
      getBitcoinChartUseCase(bitcoinChartRequest).collect {
        // ui databinding will handle errors
        _chartApiStatus.value = it.status

        if (it.data != null) {
          _chartData.value = it.data
        }
      }
    }
  }

  /**
   * Call this method to change the time span - when [bitcoinChartTimeSpan] is null the chart will just reload i.e. keep the same time span
   */
  fun getBitcoinChart(bitcoinChartTimeSpan: BitcoinChartTimeSpan? = null) {
    bitcoinChartTimeSpan?.let { bitcoinChartRequest.bitcoinChartTimeSpan = bitcoinChartTimeSpan }
    getBitcoinChart()
  }

}