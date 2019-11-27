package com.gm.hmi.gmanalytics.computedata.dataoperations

import com.gm.hmi.gmanalytics.dto.InfoDto
import com.gm.hmi.gmanalytics.util.helpers.ConversionHelper
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.math.abs

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

    private var screenDataMap = hashMapOf<String, List<DateDurationCount>>()
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

        prepareScreenDateDuration(sortedDataByDate, classNames)

        for (dateInMillis in dates) {
            screenCountByDateList.add(
                DateCount(
                    dateInMillis,
                    getScreenDurationByDate(dateInMillis).toInt()
                )
            )
        }

        return screenCountByDateList
    }

    private fun prepareScreenDateDuration(
        sortedDataByDate: List<InfoDto.EventInfo>,
        classNames: Set<String>
    ) {
        var index = 0
        var frequencyCount = 0
        var screenFirstTimestamp: Long = 0
        var screenDuration: Long
        var screenCount = 0
        var isScreenAnalysisEnabled = false
        val tempSortedDataByDate = arrayListOf<InfoDto.EventInfo>()
        tempSortedDataByDate.addAll(sortedDataByDate)
        for (className in classNames) {
            for (value in tempSortedDataByDate) {
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

                        val result: DateDurationCount
                        val data = arrayListOf<DateDurationCount>()

                        if (screenDataMap.containsKey(className)) {
                            val screenData = screenDataMap[className]
                            screenData?.toList()?.let { data.addAll(it) }

                            result = DateDurationCount(
                                screenFirstTimestamp,
                                screenDuration,
                                screenCount
                            )
                            data.add(result)
                        } else {
                            result =
                                DateDurationCount(
                                    screenFirstTimestamp,
                                    screenDuration,
                                    screenCount
                                )
                            data.add(result)
                        }
                        screenDataMap[className] = data
                    }
                    index = 0
                    screenCount = 0
                    screenFirstTimestamp = 0
                }
            }
            frequencyCount = 0
        }
    }

    private fun getScreenCountByDate(dateInMillis: Long): Int {
        var screenCount = 0
        for ((key, value) in screenDataMap) {
            screenCount += value.count {
                ConversionHelper.removeTimeFromDate(it.date) == dateInMillis
            }
        }
        return screenCount
    }

    private fun getScreenDurationByDate(
        dateInMillis: Long
    ): Long {
        var nameVsDuration = hashMapOf<String, Long>()
        var duration = 0L
        for ((key, value) in screenDataMap) {
            for (dateDurationCount in value) {
                val firstTimeStamp = ConversionHelper.removeTimeFromDate(dateDurationCount.date)
                if (dateInMillis == firstTimeStamp) {
                    duration += abs(TimeUnit.MILLISECONDS.toHours(dateDurationCount.duration))
                }
            }
        }
        return duration
    }

class DateCount(var date: Long, var count: Int)
class DateDurationCount(var date: Long, var duration: Long, var count: Int)

}