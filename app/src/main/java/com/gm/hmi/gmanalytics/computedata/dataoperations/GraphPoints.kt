package com.gm.hmi.gmanalytics.computedata.dataoperations

import com.gm.hmi.gmanalytics.computedata.fileoperations.FileOperations
import com.gm.hmi.gmanalytics.util.helpers.ConversionHelper
import com.jjoe64.graphview.series.DataPoint
import java.util.*
import kotlin.collections.ArrayList

/**
 * Data points which are required by the graph
 */
class GraphPoints {

    private val fileOperations = FileOperations()
    private val dataHelper = DataHelper(fileOperations.getCollectedData())

    /**
     * get Date wise event count as DataPoint for Graph
     */
    fun getEventsCountVsDateDataPointList(): List<DataPoint> {
        val dateCountList = dataHelper.getEventCountByDate()
        val dataEvents = arrayListOf<DataPoint>()
        for (date in dateCountList) {
            dataEvents.add(
                DataPoint(
                    date.date.toDouble(),
                    date.count.toDouble()
                )
            )
        }
        return dataEvents.sortedWith(compareBy { it.x })
    }

    /**
     * get Date wise screen count as DataPoint for Graph
     */
    fun getScreenCountVsDateDataPointList(): List<DataPoint> {
        val dateCountList = dataHelper.getScreenCountByDate()
        val dataEvents = arrayListOf<DataPoint>()
        for (date in dateCountList) {
            dataEvents.add(
                DataPoint(
                    date.date.toDouble(),
                    date.count.toDouble()
                )
            )
        }
        return dataEvents.sortedWith(compareBy { it.x })
    }

    /**
     * get Date wise screen duration as DataPoint for Graph
     */
    fun getScreenDurationVsDateDataPointsList(): List<DataPoint> {
        val dataDurationsList = dataHelper.getScreenDurationByDate()
        val dataEvents = arrayListOf<DataPoint>()
        for (date in dataDurationsList) {
            dataEvents.add(
                DataPoint(
                    date.date.toDouble(),
                    date.duration.toDouble()
                )
            )
        }

        return dataEvents.sortedWith(compareBy { it.x })
    }


    //<editor-fold desc="Mock Data for testing...">
    fun mockData(dataEvents: ArrayList<DataPoint>) {
        var dateMock = "23/11/2019"
        // Adding dashboard_overview_list data
        for (i in 1..10) {
            when (i) {
                in 3..4 -> dateMock = "24/11/2019"
                in 5..7 -> dateMock = "25/11/2019"
                in 8..10 -> dateMock = "26/11/2019"
            }

            dataEvents.add(
                DataPoint(
                    Date(ConversionHelper.convertDateToMills(dateMock)),
                    (0..100).random().toDouble()
                )
            )
        }

    }
    //</editor-fold>
}