package com.gm.hmi.gmanalytics.views

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.gm.hmi.gmanalytics.R
import kotlinx.android.synthetic.main.dashboard_main.view.*

class DetailsFragment : Fragment() {

    val shownIndex: Int by lazy {
        arguments?.getInt("index", 0) ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist. The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // isn't displayed. Note this isn't needed -- we could just
            // run the code below, where we would create and return the
            // view hierarchy; it would just never be used.
            return null
        }

        val view = activity?.findViewById<LinearLayout>(R.id.dashboard_main)
        return ScrollView(activity).apply {
//                        addView(view)
        }

//        val text = TextView(activity).apply {
//            val padding: Int = TypedValue.applyDimension(
//                TypedValue.COMPLEX_UNIT_DIP,
//                4f,
//                activity?.resources?.displayMetrics
//            ).toInt()
//            setPadding(padding, padding, padding, padding)
//            text = "Test text"
//        }
//        return ScrollView(activity).apply {
//            addView(text)
//        }
    }

    companion object {
        /**
         * Create a new instance of DetailsFragment, initialized to
         * show the text at 'index'.
         */
        fun newInstance(index: Int): DetailsFragment {
            val f = DetailsFragment()

            // Supply index input as an argument.
            val args = Bundle()
            args.putInt("index", index)
            f.arguments = args

            return f
        }
    }
}