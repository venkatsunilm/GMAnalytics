package com.gm.hmi.gmanalytics.util

import android.net.ParseException
import java.text.SimpleDateFormat
import java.util.*



class Helper {

    companion object {

        fun getMilliFromDate(dateFormat: String): Long {
            var date = Date()
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            try {
                date = formatter.parse(dateFormat)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            println("Today is $date")
            return date.time
        }

        /**
         * Helper method to convert milli seconds time to String by applying a default pattern
         * @param milliSeconds: time in milli seconds
         * @param datePattern: by default pattern "dd-MM-yyyy HH:MM" is applied, pass a required pattern to override the default
         *
         * @return formatted date text
         */
        fun convertMillisToDatePattern(
            milliSeconds: Long,
            datePattern: String = "dd-MM-yyyy HH:MM"
        ): String {
            return SimpleDateFormat(datePattern, Locale.getDefault()).format(Date(milliSeconds))
        }
    }
}