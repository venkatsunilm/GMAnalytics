package com.gm.hmi.gmanalytics.util.helpers

import android.net.ParseException
import java.text.SimpleDateFormat
import java.util.*


class ConversionHelper {

    companion object {

        private const val datePatternDefault = "dd/MM/yyyy"

//        TODO: Add overloading parameter datePattern by assigning datePatternDefault
        /**
         * converts date of default format to Milli seconds
         *
         * @param dateInTextFormat: Date in a text format
         * @return date in milli seconds
         */
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
         * Converts milli seconds time to String by applying a default pattern
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

        /**
         * Removes time only from Date provided in Milli seconds
         *
         * @param dateInMillis
         * @return time in milli seconds
         */
        fun removeTimeFromDate(dateInMillis: Long): Long =
            convertDateToMills(
                convertMillisToDatePattern(
                    dateInMillis
                )
            )
    }
}