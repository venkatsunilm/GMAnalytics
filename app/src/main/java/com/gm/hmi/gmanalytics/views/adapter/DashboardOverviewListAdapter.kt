package com.gm.hmi.gmanalytics.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gm.hmi.gmanalytics.R


class DashboardOverviewListAdapter(val overviewHeaderValueList: List<OverviewHeaderValueModel>) :
    RecyclerView.Adapter<DashboardOverviewListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dashboardOverviewListHeader: TextView =
            itemView.findViewById(R.id.dashboardOverviewListHeader) as TextView
        var dashboardOverviewListValue: TextView =
            itemView.findViewById(R.id.dashboardOverviewListValue) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val dashboardOverviewView =
            inflater.inflate(R.layout.dashboard_overview_item, parent, false)

        // Return a new holder instance
        return ViewHolder(dashboardOverviewView)
    }

    override fun getItemCount(): Int {
        return overviewHeaderValueList.size
    }

    override fun onBindViewHolder(holder: DashboardOverviewListAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val overviewHeaderValueData = overviewHeaderValueList.get(position)

        holder.dashboardOverviewListHeader.text = overviewHeaderValueData.header
        holder.dashboardOverviewListValue.text = overviewHeaderValueData.value.toString()
    }


//    private var lastContactId = 0

//    fun createContactsList(count: Int): ArrayList<OverviewHeaderValueModel> {
//        val headerValueList = ArrayList<OverviewHeaderValueModel>()
//
//        for (i in 1..count) {
//            headerValueList.add(
//                OverviewHeaderValueModel(
//                    "Header " + ++lastContactId,
//                    "Value " + ++lastContactId
//                )
//            )
//        }
//
//        return headerValueList
//    }

}

data class OverviewHeaderValueModel(val header: String, val value: Long)