package io.n26btc.presentation

import android.app.Application
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import io.n26btc.presentation.livedata.SingleLiveEvent

open class BaseViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {
  val internetErrorLiveData = SingleLiveEvent<Unit>()
  val genericErrorLiveData = SingleLiveEvent<Unit>()
  val showMessageResId = SingleLiveEvent<@StringRes Int?>()
}
