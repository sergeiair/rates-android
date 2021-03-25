package com.sz.ratespal.utils

import android.graphics.Color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.sz.ratespal.entities.RatesItem
import java.text.SimpleDateFormat
import java.util.*


class LineChartUtil {

    companion object {
        private val values = mutableListOf<Entry>()

        private var pair: String = "EURUSD"

        private var dataSet = LineDataSet(values, pair)

        fun prepare(pairCode: String, data: List<RatesItem>?): LineData {
            pair = pairCode
            dataSet.label = pair

            return getValues(data).let {
                dataSet = LineDataSet(it, pair)

                LineData(dataSet)
            }
        }

        fun withDateXAxis(chart: LineChart) {
            chart.xAxis.isEnabled = true
            chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
            chart.xAxis.valueFormatter = object: ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                    return values[value.toInt()]
                        ?.let { Date(it.x.toLong()) }
                        ?.let { SimpleDateFormat("dd", Locale.ENGLISH).format(it) } ?: ""
                }
            }
        }
        
        fun decorate() {
            dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
            dataSet.cubicIntensity = 0.2f
            dataSet.setDrawFilled(true)
            dataSet.setDrawCircles(false)
            dataSet.lineWidth = 1.8f
            dataSet.circleRadius = 4f
            dataSet.setCircleColor(Color.WHITE)
            dataSet.highLightColor = Color.rgb(244, 117, 117)
            dataSet.color = Color.GREEN
            dataSet.fillColor = Color.GRAY
            dataSet.fillAlpha = 100
            dataSet.setDrawHorizontalHighlightIndicator(false)





           /*dataSet.fillFormatter =
                IFillFormatter { dataSet, dataProvider -> binding.ratesChart.getAxisLeft().getAxisMinimum() }*/
        }

        private fun getUpdatedDataSet(values: List<Entry>): LineDataSet {
            dataSet.clear()

            values.forEach {
                dataSet.addEntry(it)
            }

            return dataSet
        }

        private fun getValues(data: List<RatesItem>?): List<Entry> {
            values.clear()

            data?.forEachIndexed { index, value ->
                if (index <= 4) {
                    values.add(Entry(index.toFloat(), 20 + value[pair].toFloat()))
                }
            }

            Print.i(values)
        /*    values.clear()


            for (i in 0 until 4) {
                val `val` = (Math.random() * (1 + 1)).toFloat() + 20
                values.add(Entry(i.toFloat(), `val`))
            }

            Print.i(values)*/

            return values
        }
    }

}