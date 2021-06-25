package io.n26btc.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import io.n26btc.extensions.visibleOrGone

@BindingAdapter("app:selected") fun selected(view: View, selected: Boolean?) {
  selected?.let { view.isSelected = selected }
}

@BindingAdapter("app:visibleOrGone") fun visibleOrGone(view: View, visible: Boolean?) {
  visible ?: return
  view.visibleOrGone(visible)
}