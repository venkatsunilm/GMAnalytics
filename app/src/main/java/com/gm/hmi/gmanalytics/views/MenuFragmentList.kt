package com.gm.hmi.gmanalytics.views

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.ListFragment
import com.gm.hmi.gmanalytics.R

class MenuFragmentList : ListFragment() {

    private var dualPane: Boolean = false
    private var curCheckPosition = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Populate list with our static array of titles.
        listAdapter = activity?.let {
            ArrayAdapter<String>(
                it,
                R.layout.menu_item,
                arrayOf(
                    "Dashboard",
                    "App Summary",
                    "App Details",
                    "Event Summary",
                    "Event Details",
                    "Device Summary",
                    "Device Details",
                    "Geography",
                    "Admin"
                )
            )
        }


        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        val detailsFrame: View? =
            activity?.findViewById<View>(com.gm.hmi.gmanalytics.R.id.dashboardgraphDetails)
        dualPane = detailsFrame?.visibility == View.VISIBLE

        curCheckPosition = savedInstanceState?.getInt("curChoice", 0) ?: 0

        if (dualPane) {
            // In dual-pane mode, the list view highlights the selected item.
            listView.choiceMode = ListView.CHOICE_MODE_SINGLE
            // Make sure our UI is in the correct state.
            showDetails(curCheckPosition)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("curChoice", curCheckPosition)
    }

//    override fun getView(): View? {
//        getView()?.setBackgroundColor(Color.BLUE)
//        return super.getView()
//    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        showDetails(position)
    }

    /**
     * Helper function to show the details of a selected item, either by
     * displaying a fragment in-place in the current UI, or starting a
     * whole new activity in which it is displayed.
     */
    private fun showDetails(index: Int) {
        curCheckPosition = index

        if (dualPane) {
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            listView.setItemChecked(index, true)

            // Check what fragment is currently shown, replace if needed.
            var details =
                fragmentManager?.findFragmentById(com.gm.hmi.gmanalytics.R.id.dashboardgraphDetails) as? DetailsFragment
            if (details?.shownIndex != index) {
                // Make new fragment to show this selection.
                details = DetailsFragment.newInstance(index)

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                fragmentManager?.beginTransaction()?.apply {
                    if (index == 0) {
                        replace(com.gm.hmi.gmanalytics.R.id.dashboardgraphDetails, details)
                    } else {
//                        replace(android.R.id.a_item, details)
                    }
                    setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    commit()
                }
            }

        }
//        else {
//            // Otherwise we need to launch a new activity to display
//            // the dialog fragment with selected text.
//            val intent = Intent().apply {
//                setClass(activity, DetailsActivity::class.java)
//                putExtra("index", index)
//            }
//            startActivity(intent)
//        }
    }

}