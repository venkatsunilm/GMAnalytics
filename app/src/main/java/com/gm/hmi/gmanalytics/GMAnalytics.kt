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
import com.jjoe64.graphview.series.DataPoint
import kotlinx.android.synthetic.main.activity_gmanalytics.*
import kotlinx.android.synthetic.main.dashboard_graph_views.view.*


class GMAnalytics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmanalytics)
        requestPermission(this)

        renderTheDashboardGraphs()
    }

    private fun renderTheDashboardGraphs() {

        // App duration graph
        var dataPointsList = arrayListOf<List<DataPoint>>()
        dataPointsList.add(GraphPoints().getEventsCountDataPointList())
        DrawLineGraph().renderTheGraph(
            dashBoardGraphViewID.appDurationGraph,
            "App duration",
            dataPointsList
        )

        // Screen duration graph
        dataPointsList.clear()
        dataPointsList.add(GraphPoints().getScreenDurationDataPointsList())
        DrawLineGraph().renderTheGraph(
            dashBoardGraphViewID.screenDurationGraph,
            "Screen duration",
            dataPointsList
        )

        // App sessions graph
        dataPointsList.clear()
        dataPointsList.add(GraphPoints().getScreenCountDataPointList())
        DrawLineGraph().renderTheGraph(
            dashBoardGraphViewID.appSessionsGraph,
            "App Count",
            dataPointsList
        )

        // Event count graph
        dataPointsList.clear()
        dataPointsList.add(GraphPoints().getEventsCountDataPointList())
        DrawLineGraph().renderTheGraph(
            dashBoardGraphViewID.eventCountGraph,
            "Event Count",
            dataPointsList
        )

        // Page view count graph
        dataPointsList.clear()
        dataPointsList.add(GraphPoints().getScreenCountDataPointList())
        DrawLineGraph().renderTheGraph(
            dashBoardGraphViewID.pageViewsGraph,
            "Page views",
            dataPointsList
        )
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
