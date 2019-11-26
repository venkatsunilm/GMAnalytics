package com.gm.hmi.gmanalytics.computedata.dataoperations

import com.gm.hmi.gmanalytics.computedata.fileoperations.FileOperations
import com.gm.hmi.gmanalytics.util.helpers.ConversionHelper
import com.jjoe64.graphview.series.DataPoint
import java.util.*
import kotlin.collections.ArrayList

class GraphPoints {

    fun getEventsDataPointList(): List<DataPoint> {
        val fileOperations = FileOperations()
        val collectedData = fileOperations.getCollectedData()

        val dataHelper = DataHelper()
        val dateCountList = dataHelper.getEventCountByDate(collectedData)

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

    fun getScreenDataPointList(): List<DataPoint> {
        val fileOperations = FileOperations()
        val collectedData = fileOperations.getCollectedData()

        val dataHelper = DataHelper()
        val dateCountList = dataHelper.getScreenCountByDate(collectedData)

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

    fun mockData(dataEvents: ArrayList<DataPoint>) {
        var dateMock = "23/11/2019"
        // Adding test data
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