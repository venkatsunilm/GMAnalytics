package com.gm.hmi.gmanalytics.computedata.dataoperations

import com.gm.hmi.gmanalytics.computedata.fileoperations.FileOperations
import com.gm.hmi.gmanalytics.util.helpers.ConversionHelper
import com.jjoe64.graphview.series.DataPoint
import java.util.*
import kotlin.collections.ArrayList

class GraphPoints {

    private val fileOperations = FileOperations()
    private val dataHelper = DataHelper(fileOperations.getCollectedData())

    fun getEventsCountDataPointList(): List<DataPoint> {
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
//        mockData(dataEvents)
//        dataEvents.clear()
//        dataEvents.add(DataPoint(0.0, 1.0))
//        dataEvents.add(DataPoint(1.0, 4.0))
//        dataEvents.add(DataPoint(2.0, -5.0))
//        dataEvents.add(DataPoint(3.0, 1.0))
//        dataEvents.add(DataPoint(4.0, 0.0))
//        dataEvents.add(DataPoint(5.0, 3.0))
//        dataEvents.add(DataPoint(6.0, 1.0))
//        dataEvents.add(DataPoint(7.0, 7.0))

        return dataEvents.sortedWith(compareBy { it.x })
    }

    fun getScreenCountDataPointList(): List<DataPoint> {
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

    fun getScreenDurationDataPointsList(): List<DataPoint> {
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

    fun mockData(dataEvents: ArrayList<DataPoint>) {
        var dateMock = "23/11/2019"
        // Adding dashboard_overview data
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
}