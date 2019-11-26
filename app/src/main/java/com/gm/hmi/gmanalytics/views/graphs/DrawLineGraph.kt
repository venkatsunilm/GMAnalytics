package com.gm.hmi.gmanalytics.views.graphs

import android.graphics.Color
import androidx.annotation.NonNull
import com.gm.hmi.gmanalytics.util.helpers.ConversionHelper
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class DrawLineGraph {

    private lateinit var series: LineGraphSeries<DataPoint>

    fun renderTheGraph(
        @NonNull sortedList: List<DataPoint>,
        @NonNull graphObject: GraphView,
        title: String
    ) {
        series = LineGraphSeries(sortedList.toTypedArray())
        series.color = Color.rgb(225, 230, 25)
        series.thickness = 10
        series.isDrawBackground = true
        series.backgroundColor = Color.argb(60, 23, 45, 222)
        series.isDrawDataPoints = true
        series.dataPointsRadius = 10f
        series.setAnimated(true)

        graphObject.addSeries(series)
        graphObject.viewport.isScrollable = true
        graphObject.viewport.setScrollableY(true)
        graphObject.title = title
        graphObject.titleColor = Color.BLACK
        graphObject.titleTextSize = 66f
        graphObject.viewport.isScalable = true
        graphObject.viewport.setScalableY(true)

        graphObject.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                return if (isValueX) {
                    // show normal x values
                    ConversionHelper.convertMillisToDatePattern(value.toLong(), "MMM dd")
                } else {
                    // show currency for y values
                    super.formatLabel(value, isValueX)
                }
            }
        }

    }

}