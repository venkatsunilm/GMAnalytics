package com.gm.hmi.gmanalytics.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gm.hmi.gmanalytics.R


/**
 * Dashboard Header and its value list Adapter, helps to render the combination and to update data dynamically
 *
 * @param overviewHeaderValueList: List of items holding Header and value
 */

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

}

data class OverviewHeaderValueModel(val header: String, val value: Long)