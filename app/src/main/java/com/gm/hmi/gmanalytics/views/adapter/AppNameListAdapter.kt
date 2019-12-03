package com.gm.hmi.gmanalytics.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.gm.hmi.gmanalytics.R

//TODO: To add image with app name in future

/**
 * Application Name and Icon list Adapter class, helps to render the combination and to update data dynamically
 *
 * @param appNameImageToggleList: List of items holding NameImageModel
 */
class AppNameListAdapter(val appNameImageToggleList: List<NameImageModel>) :
    RecyclerView.Adapter<AppNameListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var appNameToggleButton: ToggleButton =
            itemView.findViewById(R.id.appNameToggleButton) as ToggleButton
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout
        val appNameImageItemView =
            inflater.inflate(R.layout.app_name_image_item, parent, false)

        // Return a new holder instance

        return ViewHolder(appNameImageItemView)
    }

    override fun getItemCount(): Int {
        return appNameImageToggleList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = appNameImageToggleList[position]

        holder.appNameToggleButton.text = item.name
        holder.appNameToggleButton.textOff = item.name
        holder.appNameToggleButton.textOn = item.name
    }

}

data class NameImageModel(val name: String/*, val image: Image*/)