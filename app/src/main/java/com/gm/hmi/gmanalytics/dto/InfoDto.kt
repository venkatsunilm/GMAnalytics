package com.gm.hmi.gmanalytics.dto

class InfoDto {

    data class AppInfo(val id: String,
                       val name: String,
                       val count: Int,
                       val screenInfoList: Map<String, ScreenInfo>,
                       val firstTimeStamp: Long = 0L,
                       val lastTimeStamp: Long = 0L,
                       val lastTimeUsed: Long = 0L,
                       val lastTimeVisible: Boolean = false,
                       val totalTimeInForeground: Long = 0L
    )

    data class ScreenInfo(val id: String,
                          val name: String,
                          val count: Int,
                          val eventInfoList: Map<String, EventInfo>,
                          val firstTimeStamp: Long = 0L,
                          val lastTimeStamp: Long = 0L,
                          val lastTimeUsed: Long = 0L,
                          val eventType: Int = 0,
                          val totalTimeUsed: Long = 0L)

    data class EventInfo(val id: String?,
                         val name: String?,
                         val count: Int,
                         val firstTimeStamp: Long = 0L,
                         val lastTimeStamp: Long = 0L,
                         val lastTimeUsed: Long = 0L,
                         val eventType: Int = 0,
                         val totalTimeUsed: Long = 0L) {
    }


}

