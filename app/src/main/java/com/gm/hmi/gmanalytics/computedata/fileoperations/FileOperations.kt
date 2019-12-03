package com.gm.hmi.gmanalytics.computedata.fileoperations

import android.os.Environment
import com.gm.hmi.gmanalytics.dto.InfoDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jjoe64.graphview.series.DataPoint
import java.io.File

class FileOperations(private val fileName: String = "TestApp.json") {

    // TODO: optional: Use File provider to secure sharing of files associated with an app by creating a content:// Uri
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

    private fun readFile(): ArrayList<InfoDto.EventInfo> {
        var info = ArrayList<InfoDto.EventInfo>()
        when {
            isFileExists() -> {
                val jsonFromMap = fileObject.readText()
                info = gson.fromJson(
                    jsonFromMap,
                    object : TypeToken<ArrayList<InfoDto.EventInfo>>() {}.type
                )
            }
        }
        return info
    }

    private fun closeFile() {
        when (isFileExists()) {
        }
    }

    fun getCollectedData(): ArrayList<InfoDto.EventInfo> {
        return eventInfo
    }


}
