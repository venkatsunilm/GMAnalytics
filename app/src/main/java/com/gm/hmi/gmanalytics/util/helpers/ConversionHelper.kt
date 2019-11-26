package com.gm.hmi.gmanalytics.util.helpers

import android.net.ParseException
import java.text.SimpleDateFormat
import java.util.*


class ConversionHelper {

    companion object {

        private const val datePatternDefault = "dd/MM/yyyy"

        fun convertDateToMills(dateInTextFormat: String): Long {
            var date: Date? = Date()
            val formatter = SimpleDateFormat(datePatternDefault, Locale.getDefault())
            try {
                date = formatter.parse(dateInTextFormat)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return date!!.time
        }

        /**
         * ConversionHelper method to convert milli seconds time to String by applying a default pattern
         * @param milliSeconds: time in milli seconds
         * @param datePattern: by default pattern "dd-MM-yyyy HH:MM" is applied, pass a required pattern to override the default
         *
         * @return formatted date text
         */
        fun convertMillisToDatePattern(
            milliSeconds: Long,
            datePattern: String = datePatternDefault
        ): String {
            return SimpleDateFormat(datePattern, Locale.getDefault()).format(Date(milliSeconds))
        }

        fun removeTimeFromDate(dateInMillis: Long): Long =
            convertDateToMills(
                convertMillisToDatePattern(
                    dateInMillis
                )
            )
    }
}