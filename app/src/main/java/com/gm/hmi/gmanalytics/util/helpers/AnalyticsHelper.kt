package com.gm.hmi.gmanalytics.util.helpers

class AnalyticsHelper {

    /**
     * Compare two dates in milli seconds using datePatternDefault
     *
     * @param dateMillis1
     * @param dateMillis2
     *
     * @return true or false
     */
    fun compareTwoDatePatterns(dateMillis1: Long, dateMillis2: Long): Boolean =
        ConversionHelper.convertMillisToDatePattern(
            dateMillis1
        ) == ConversionHelper.convertMillisToDatePattern(dateMillis2)
}