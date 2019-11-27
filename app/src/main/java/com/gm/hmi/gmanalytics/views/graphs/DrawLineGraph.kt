package com.gm.hmi.gmanalytics.views.graphs

import android.graphics.Color
import androidx.annotation.NonNull
import com.gm.hmi.gmanalytics.util.helpers.ConversionHelper
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class DrawLineGraph {

    private lateinit var series: LineGraphSeries<DataPoint>
    private lateinit var series2: LineGraphSeries<DataPoint>

    fun renderTheGraph(
        @NonNull graphObject: GraphView,
        title: String,
        @NonNull sortedList: List<List<DataPoint>>
    ) {

        var index = 0
        for (listItem in sortedList) {
            index++
            series = LineGraphSeries(listItem.toTypedArray())
//            series.color = Color.rgb(225 + index, 230 + index, 25 + index)
            when (index) {
                0 -> {
                    series.color = Color.rgb(255, 195, 0)
                    series.backgroundColor = Color.argb(20, 255, 195, 0)
                }
                1 -> {
                    series.color = Color.rgb(10, 214, 182)
                    series.backgroundColor = Color.argb(20, 10, 214, 182)
                }
            }

            series.thickness = 10
            series.isDrawBackground = true
            series.isDrawDataPoints = true
            series.dataPointsRadius = 10f
            series.setAnimated(true)
            series.title = "Project$index"

            graphObject.addSeries(series)

        }

        graphObject.viewport.isScrollable = true
        graphObject.viewport.setScrollableY(true)
        graphObject.title = title
        graphObject.legendRenderer.isVisible = true
        graphObject.legendRenderer.textSize = 40f
        graphObject.legendRenderer.textColor = Color.WHITE
        graphObject.legendRenderer.align = LegendRenderer.LegendAlign.TOP
        graphObject.legendRenderer.spacing = 10
        graphObject.legendRenderer.margin = 10
        graphObject.titleColor = Color.BLACK
        graphObject.titleTextSize = 66f
        graphObject.setBackgroundColor(Color.WHITE)
        graphObject.viewport.isScalable = true
        graphObject.viewport.setScalableY(true)
        graphObject.gridLabelRenderer.numHorizontalLabels = 4

        graphObject.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                return when {
                    isValueX -> // show normal x values
                        ConversionHelper.convertMillisToDatePattern(value.toLong(), "MMM dd")
                    else -> // show y values
                        super.formatLabel(value, isValueX)
                }
            }
        }

    }

}