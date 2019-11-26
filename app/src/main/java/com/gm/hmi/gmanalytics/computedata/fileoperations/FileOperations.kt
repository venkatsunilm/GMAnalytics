package com.gm.hmi.gmanalytics.computedata.fileoperations

import android.os.Environment
import com.gm.hmi.gmanalytics.dto.InfoDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class FileOperations(private val fileName: String = "TestApp.json") {

    // TODO: Use File provider to secure sharing of files associated with an app by creating a content:// Uri
    private var fileObject: File
    private var pathName: String = Environment.getExternalStorageDirectory().toString()
    private val gson = Gson()
    private var eventIndex = 1
    private var screenIndex = 1
    private var eventInfoMapData = hashMapOf<String, InfoDto.EventInfo>()
    private var screenInfoMapData = hashMapOf<String, InfoDto.ScreenInfo>()
    private var eventInfo: ArrayList<InfoDto.EventInfo>

    init {
        fileObject = File(pathName, fileName)
        eventInfo = readFile()
        closeFile()
    }

    private fun isFileExists(): Boolean {
        return when {
            File(pathName, fileName).exists() -> true
            else -> false
        }
    }

    private fun createFile() {
        when {
            !isFileExists() -> {
                fileObject.createNewFile()
//                screenInfoMapData.putAll(eventInfo.screenInfoList)

            }
            else -> {

                // retrieve the data from the file system
                var retrievedData = readFile()

                // Assign the values with the present map object which is passed.
                screenInfoMapData.clear()
                eventInfoMapData.clear()
//                for ((keyScreen, valueScreen) in retrievedData.screenInfoList) {
//                    for ((keyEvent, valueEvent) in valueScreen.eventInfoList) {
//                        updateEventData(keyScreen, keyEvent)
//                    }
//                }
//
//                // Update the events to the existing screenInfoMapData & eventInfoMapData
//                for ((keyScreen, valueScreen) in eventInfo.screenInfoList) {
//                    for ((keyEvent, valueEvent) in valueScreen.eventInfoList) {
//                        updateEventData(keyScreen, keyEvent)
//                    }
//                }
            }
        }
    }

    private fun writeFile() {
        when {
            isFileExists() -> {
                val jsonFromMap = gson.toJson(screenInfoMapData)
                fileObject.writeText(jsonFromMap)
            }
        }
    }

    private fun readFile(): ArrayList<InfoDto.EventInfo> {
        var info = ArrayList<InfoDto.EventInfo>()
        when {
            isFileExists() -> {
                val jsonFromMap = fileObject.readText()
                info = gson.fromJson(jsonFromMap, object : TypeToken<ArrayList<InfoDto.EventInfo>>() {}.type)
            }
        }
        return info
    }

    private fun closeFile() {
        when (isFileExists()) {
        }
    }

//    private fun updateEventData(screenName: String, eventName: String) {
//        if (!screenInfoMapData.containsKey(screenName)) {
//            eventIndex = 1
//            eventInfoMapData = hashMapOf<String, InfoDto.EventInfo>()
//        }
//
//        when {
//            eventInfoMapData.containsKey(eventName) -> {
//                val eventData = eventInfoMapData[eventName]
//                val eventInfo = InfoDto.EventInfo(
//                    eventData!!.id, eventData.name,
//                    eventData.count + 1
//                )
//                eventInfoMapData[eventName] = eventInfo
//            }
//            else -> {
//                val eventInfo = InfoDto.EventInfo("E_" + eventIndex++, eventName, 1)
//                eventInfoMapData[eventName] = eventInfo
//            }
//        }
//
//        updateScreenData(screenName)
//    }

//    private fun updateScreenData(screenName: String) {
//        when {
//            screenInfoMapData.containsKey(screenName) -> {
//                val screenData = screenInfoMapData[screenName]
//                val screenInfo = InfoDto.ScreenInfo(
//                    screenData!!.id, screenData.name,
//                    screenData.count + 1, eventInfoMapData
//                )
//                screenInfoMapData[screenName] = screenInfo
//            }
//            else -> {
//                val screenInfo = InfoDto.ScreenInfo(
//                    "S_" + screenIndex++,
//                    screenName, 1, eventInfoMapData
//                )
//                screenInfoMapData[screenName] = screenInfo
//            }
//        }
//    }

    fun getCollectedData(): ArrayList<InfoDto.EventInfo> {
        return eventInfo
    }


}
