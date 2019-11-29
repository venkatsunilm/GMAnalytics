package com.gm.hmi.gmanalytics.views.adapter

import android.media.Image
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AppNameListAdapter(val overviewHeaderValueList: List<NameImageModel>) :
    RecyclerView.Adapter<DashboardOverviewListAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DashboardOverviewListAdapter.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: DashboardOverviewListAdapter.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

data class NameImageModel(val name: String, val image: Image)