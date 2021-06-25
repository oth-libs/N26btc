package io.n26btc.presentation

import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.n26btc.presentation.livedata.SingleLiveEvent

open class BaseViewModel : ViewModel(), LifecycleObserver {
  val internetErrorLiveData = SingleLiveEvent<Unit>()
  val genericErrorLiveData = SingleLiveEvent<Unit>()
  val showMessageResId = SingleLiveEvent<@StringRes Int?>()
}
