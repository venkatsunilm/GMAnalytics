package com.gm.hmi.gmanalytics.computedata.dataoperations

import com.gm.hmi.gmanalytics.dto.InfoDto
import com.gm.hmi.gmanalytics.util.helpers.ConversionHelper

class DataHelper {

    fun getEventCountByDate(collectedData: ArrayList<InfoDto.EventInfo>/*, from: Long, to: Long*/): ArrayList<DateCount> {

        val sortedDataByDate =
            collectedData.sortedWith(compareBy { it.firstTimeStamp })
        val eventNames = hashSetOf<String>()
        val dates = hashSetOf<Long>()
        val eventCountByDateList = arrayListOf<DateCount>()

        for (value in sortedDataByDate) {
            val dateWithoutTime = ConversionHelper.removeTimeFromDate(value.firstTimeStamp)
            eventNames.add(value.eventName) // collect all the event names without duplicates
            dates.add(dateWithoutTime) // collect all the Timestamp without duplicates

            value.firstTimeStamp = dateWithoutTime
        }

        for (dateInMillis in dates) {
            eventCountByDateList.add(
                DateCount(
                    dateInMillis,
                    sortedDataByDate.count {
                        it.firstTimeStamp == dateInMillis
                    }
                )
            )
        }
        return eventCountByDateList
    }


    private var screenDataMap = hashMapOf<String, DateDurationCount>()
    fun getScreenCountByDate(collectedData: ArrayList<InfoDto.EventInfo>): ArrayList<DateCount> {
        val sortedDataByDate =
            collectedData.sortedWith(compareBy { it.firstTimeStamp })
        val classNames = hashSetOf<String>()
        val dates = hashSetOf<Long>()
        val screenCountByDateList = arrayListOf<DateCount>()

        for (value in sortedDataByDate) {
            val dateWithoutTime = ConversionHelper.removeTimeFromDate(value.firstTimeStamp)
            classNames.add(value.className)// collect all the event names without duplicates
            dates.add(dateWithoutTime) // collect all the Timestamp without duplicates
            value.firstTimeStamp = dateWithoutTime
        }

        var index = 0
        var frequencyCount = 0
        var screenFirstTimestamp: Long = 0
        var screenDuration: Long
        var screenCount = 0
        var isScreenAnalysisEnabled = false
        for (className in classNames) {
            for (value in sortedDataByDate) {
                if (value.className == className) {
                    if (index == 0) {
                        screenFirstTimestamp = value.firstTimeStamp
                        screenCount++
                        isScreenAnalysisEnabled = true
                    }
                    index++
//                    screenDuration = value.firstTimeStamp - screenFirstTimestamp
                } else {
                    if (isScreenAnalysisEnabled) {
                        isScreenAnalysisEnabled = false
                        screenDuration = value.firstTimeStamp - screenFirstTimestamp
                        val result =
                            DateDurationCount(
                                screenFirstTimestamp,
                                screenDuration.toInt(),
                                screenCount
                            )
                        screenDataMap[className + "_" + frequencyCount++] = result
                    }
                    index = 0
                    screenCount = 0
                    screenFirstTimestamp = 0
                }
            }
            frequencyCount = 0
        }

        for (dateInMillis in dates) {
            screenCountByDateList.add(
                DateCount(
                    dateInMillis,
                    getScreenCount(dateInMillis)
                )
            )
        }

        return screenCountByDateList
    }


    private fun getScreenCount(dateInMillis: Long): Int {

        return screenDataMap.count {
            ConversionHelper.removeTimeFromDate(it.value.date) == dateInMillis
        }

//        var count = 0
//        for ((key, value) in screenDataMap) {
//            val firstTimeStamp = ConversionHelper.removeTimeFromDate(value.date)
//            if (dateInMillis == firstTimeStamp) {
//                count++
//            }
//        }
//        return count
    }

    class DateCount(var date: Long, var count: Int)
    class DateDurationCount(var date: Long, var duration: Int, var count: Int)

}