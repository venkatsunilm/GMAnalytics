package com.gm.hmi.gmanalytics.views.graphs

import android.graphics.Color
import android.view.View
import androidx.annotation.NonNull
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint

class DrawBarGraph {

    private lateinit var sortedList: List<DataPoint>
    private lateinit var series: BarGraphSeries<DataPoint>

    fun renderTheGraph(
        @NonNull sortedList: List<DataPoint>,
        @NonNull graphObject: GraphView,
        title: String
    ) {
        series = BarGraphSeries(sortedList.toTypedArray())
        series.color = Color.BLUE/*Color.rgb(225, 230, 25)*/
//        series.isDrawValuesOnTop = true
//        series.valuesOnTopColor = Color.RED
//        series.valuesOnTopSize = 45f
//        series.dataWidth = 40.0
//        series.spacing = 40
//        series.title = "Bar Graph Sample"
        series.isAnimated = true

        series.setValueDependentColor { Color.BLUE }

        graphObject.addSeries(series)
        graphObject.textDirection = View.TEXT_DIRECTION_ANY_RTL
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
                    super.formatLabel(value, isValueX)
                } else {
                    // show currency for y values
                    "Main Activity"
                }
            }
        }

    }
}