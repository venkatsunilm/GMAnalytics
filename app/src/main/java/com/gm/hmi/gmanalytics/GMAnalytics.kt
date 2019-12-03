package com.gm.hmi.gmanalytics

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gm.hmi.gmanalytics.computedata.dataoperations.GraphPoints
import com.gm.hmi.gmanalytics.views.adapter.AppNameListAdapter
import com.gm.hmi.gmanalytics.views.adapter.DashboardOverviewListAdapter
import com.gm.hmi.gmanalytics.views.adapter.NameImageModel
import com.gm.hmi.gmanalytics.views.adapter.OverviewHeaderValueModel
import com.gm.hmi.gmanalytics.views.graphs.DrawLineGraph
import com.jjoe64.graphview.series.DataPoint
import kotlinx.android.synthetic.main.activity_gmanalytics.*
import kotlinx.android.synthetic.main.dashboard_graph_views.view.*
import kotlinx.android.synthetic.main.dashboard_main.*
import kotlinx.android.synthetic.main.dashboard_overview_list.*
import java.time.format.TextStyle

//TODO: Need to use Data binding concept once understood
//TODO: Make this project as a stand alone module or a library

class GMAnalytics : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmanalytics)
        requestPermission(this)

        bindDashboardOverviewListAdapterToRecycleView()
        bindAppNameImageviewListAdapterToRecycleView()
        renderTheDashboardGraphs()
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    /**
     * Bind view adapter and view manager to Dashboard overview recycler view
     */
    private fun bindDashboardOverviewListAdapterToRecycleView() {
        val headerValueList = ArrayList<OverviewHeaderValueModel>()

        val overviewList = arrayOf(
            "App Duration",
            "Screen Duration",
            "App Sesssions",
            "Page Views",
            "Event Counts",
            "New Devices",
            "Total Devices"
        )
        overviewList.reverse()
        for (element in overviewList) {
            headerValueList.add(
                OverviewHeaderValueModel(
                    element,
                    40
                )
            )
        }

        viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        viewAdapter = DashboardOverviewListAdapter(headerValueList)
        recyclerView = dashboard_overview.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    /**
     * Attach view adapter and mamager to the App Names recycler view
     */
    private fun bindAppNameImageviewListAdapterToRecycleView() {
        val appNamesList = ArrayList<NameImageModel>()
        val appNames = arrayOf(
            "HVac",
            "On Star",
            "Camera",
            "Settings",
            "Phone",
            "Updater",
            "Trailer",
            "Projections",
            "Audio",
            "SXM"
        )

        appNames.reverse()
        for (element in appNames) {
            appNamesList.add(
                NameImageModel(
                    element
                )
            )
        }

        viewManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        viewAdapter = AppNameListAdapter(appNamesList)
        recyclerView = appNameListButtons.apply {
            setHasFixedSize(false)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    /**
     * List of graphs to display sequentially
     */
    private fun renderTheDashboardGraphs() {

        // App duration graph
        var dataPointsList = arrayListOf<List<DataPoint>>()
        dataPointsList.add(GraphPoints().getEventsCountVsDateDataPointList())
        DrawLineGraph().renderTheGraph(
            dashBoardGraphViewID.appDurationGraph,
            "App duration",
            dataPointsList
        )

        // Screen duration graph
        dataPointsList.clear()
        dataPointsList.add(GraphPoints().getScreenDurationVsDateDataPointsList())
        DrawLineGraph().renderTheGraph(
            dashBoardGraphViewID.screenDurationGraph,
            "Screen duration",
            dataPointsList
        )

        // App sessions graph
        dataPointsList.clear()
        dataPointsList.add(GraphPoints().getScreenCountVsDateDataPointList())
        DrawLineGraph().renderTheGraph(
            dashBoardGraphViewID.appSessionsGraph,
            "App Count",
            dataPointsList
        )

        // Event count graph
        dataPointsList.clear()
        dataPointsList.add(GraphPoints().getEventsCountVsDateDataPointList())
        DrawLineGraph().renderTheGraph(
            dashBoardGraphViewID.eventCountGraph,
            "Event Count",
            dataPointsList
        )

        // Page view count graph
        dataPointsList.clear()
        dataPointsList.add(GraphPoints().getScreenCountVsDateDataPointList())
        DrawLineGraph().renderTheGraph(
            dashBoardGraphViewID.pageViewsGraph,
            "Page views",
            dataPointsList
        )
    }


//    TODO: Instead of file system storage use Content provider in the GMAnalytics application and pass data to that application from all apps

    /**
     * Helps to request permission to store the data in a file
     */
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
