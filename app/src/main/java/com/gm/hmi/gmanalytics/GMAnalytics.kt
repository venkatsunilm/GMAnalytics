package com.gm.hmi.gmanalytics

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.gm.hmi.gmanalytics.dto.InfoDto
import com.gm.hmi.gmanalytics.fileoperations.FileOperations
import com.gm.hmi.gmanalytics.graphs.DrawLineGraph
import com.gm.hmi.gmanalytics.util.Helper
import com.jjoe64.graphview.series.DataPoint
import kotlinx.android.synthetic.main.activity_gmanalytics.*
import java.util.*


class GMAnalytics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmanalytics)

        requestPermission(this)

        val fileOperations = FileOperations()
        val collecedData = fileOperations.getCollectedData()

        var dataEvents = arrayListOf<DataPoint>()
        var dataScreens = arrayListOf<DataPoint>()

        for ((keyScreen, valueScreen) in collecedData.screenInfoList) {
            dataScreens.add(
                DataPoint(
                    Date(valueScreen.firstTimeStamp),
                    valueScreen.count.toDouble()
                )
            )
            for ((keyEvent, valueEvent) in valueScreen.eventInfoList) {
                dataEvents.add(
                    DataPoint(
                        Date(valueEvent.firstTimeStamp),
                        valueEvent.count.toDouble()
                    )
                )
            }
        }

        val sortedScreenList = dataScreens.sortedWith(compareBy { it.x })
        DrawLineGraph().renderTheGraph(sortedScreenList, appCountGraph, "App Count")
        DrawLineGraph().renderTheGraph(sortedScreenList, screenCountGraph, "Screen Count")

        val sortedEventList = dataEvents.sortedWith(compareBy { it.x })
        DrawLineGraph().renderTheGraph(sortedEventList, eventCountGraph, "Event Count")

    }

    private val requestExternalStorage: Int = 150
    private fun requestPermission(activity: Activity) {
        val permission = ActivityCompat.checkSelfPermission(
            activity, Manifest.permission
                .WRITE_EXTERNAL_STORAGE
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                requestExternalStorage
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            requestExternalStorage -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED)
                ) {
                    Log.i(
                        GMAnalytics::class.java.simpleName,
                        "Write permission granted"
                    )
//                    gmAnalytics.commit()
                }
            }
        }
    }

}
