package com.gm.hmi.gmanalytics

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.gm.hmi.gmanalytics.computedata.dataoperations.GraphPoints
import com.gm.hmi.gmanalytics.views.graphs.DrawLineGraph
import kotlinx.android.synthetic.main.activity_gmanalytics.*


class GMAnalytics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmanalytics)

        requestPermission(this)

        DrawLineGraph().renderTheGraph(GraphPoints().getEventsDataPointList(), eventCountGraph, "Event Count")

        //        val sortedScreenList = dataScreens.sortedWith(compareBy { it.x })
//        DrawLineGraph().renderTheGraph(sortedScreenList, appCountGraph, "App Count")

        DrawLineGraph().renderTheGraph(GraphPoints().getScreenDataPointList(), screenCountGraph, "Screen Count")


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
