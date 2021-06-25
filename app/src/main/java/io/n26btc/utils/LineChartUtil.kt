package io.n26btc.utils

import android.annotation.SuppressLint
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import io.n26btc.R
import io.n26btc.domain.model.BitcoinChartModel

object LineChartUtil {

  fun LineChart.setupLineChart() {
    setNoDataText(String())

    disableGrid()
    disableLabels()
    disableMisc()

    setupMarker()
  }

  fun LineChart.updateChartData(bitcoinChartModel: BitcoinChartModel) {
    val entries = mutableListOf<Entry>()

    bitcoinChartModel.values?.forEach {
      entries.add(Entry(it.x?.toFloat() ?: 0F, it.y?.toFloat() ?: 0F))
    }

    val dataSet: LineDataSet
    if (data != null && data.dataSetCount > 0) {
      dataSet = data.getDataSetByIndex(0) as LineDataSet
      dataSet.values = entries

      dataSet.notifyDataSetChanged()
      data.notifyDataChanged()
      notifyDataSetChanged()

    } else {
      dataSet = LineDataSet(entries, "")
      dataSet.color = ContextCompat.getColor(context, R.color.green)

      dataSet.mode = LineDataSet.Mode.LINEAR
      dataSet.lineWidth = 2f
      dataSet.setDrawCircles(false)

      dataSet.setDrawVerticalHighlightIndicator(true)
      dataSet.setDrawHorizontalHighlightIndicator(false)
      dataSet.highLightColor = ContextCompat.getColor(context, R.color.highlight_color)
      dataSet.highlightLineWidth = 1f

      val dataSets = mutableListOf<ILineDataSet>(dataSet)

      this.data = LineData(dataSets)
    }

    invalidate()
  }

  @SuppressLint("ClickableViewAccessibility")
  private fun LineChart.setupMarker() {

    setOnTouchListener { _, event ->
      when (event.action) {
        MotionEvent.ACTION_UP -> {
          highlightValue(null)
        }
      }
      false
    }

    val mv = ChartMarkerView(context)

    mv.chartView = this
    marker = mv
  }

  private fun LineChart.disableGrid() {
    xAxis.apply {
      setDrawGridLines(false)
      setDrawAxisLine(false)
    }

    axisLeft.apply {
      setDrawAxisLine(false)
      isEnabled = false
    }

    axisRight.apply {
      setDrawAxisLine(false)
      setLabelCount(6, true)
      isEnabled = true
    }

    axisRight.apply {
      gridColor = ContextCompat.getColor(context, R.color.highlight_color)
      gridLineWidth = 1f

      isGranularityEnabled = true
    }
  }

  private fun LineChart.disableLabels() {
    xAxis.apply {
      setDrawLabels(true)
      position = XAxis.XAxisPosition.BOTTOM
      valueFormatter = DateAxisValueFormatter()
      textColor = ContextCompat.getColor(context, R.color.text_color_main)
      labelRotationAngle = -56f
    }

    axisRight.apply {
      setDrawLabels(true)
      textColor = ContextCompat.getColor(context, R.color.text_color_main)
      valueFormatter = AmountAxisValueFormatter()
    }

    axisLeft.setDrawLabels(false)
  }

  private fun LineChart.disableMisc() {
    isDoubleTapToZoomEnabled = false
    setScaleEnabled(false)
    setExtraOffsets(20f, 0f, 0f, 0f)

    legend.isEnabled = false
    description.text = ""
    setDrawGridBackground(false)
    setDrawBorders(false)
    setBorderWidth(0f)
  }

  class DateAxisValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
      return "Calendar.getInstance()"//.apply { timeInMillis = value.toLong() }.formatDate()
    }
  }

  class AmountAxisValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
      return "value.prettyCount()"
    }
  }

}