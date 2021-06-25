package io.n26btc.utils

import android.content.Context
import android.view.View
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.utils.MPPointF
import io.n26btc.R

class ChartMarkerView(context: Context) : MarkerView(context, R.layout.view_chart_marker) {

  private val indicator: View = findViewById(R.id.indicator)

  override fun getOffset(): MPPointF {
    return MPPointF((-(width / 2)).toFloat(), (indicator.height / -2).toFloat())
  }
}