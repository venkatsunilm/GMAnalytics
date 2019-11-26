package com.gm.hmi.gmanalytics.util.helpers

class AnalyticsHelper {

    fun compareTwoDatePatterns(dateMillis1: Long, dateMillis2: Long): Boolean =
        ConversionHelper.convertMillisToDatePattern(
            dateMillis1
        ) == ConversionHelper.convertMillisToDatePattern(dateMillis2)
}