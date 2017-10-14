package com.treecio.crowdio.ui.fragment

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.treecio.crowdio.R
import com.treecio.crowdio.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_add_performance.view.*

/**
 * Created by Vytautas on 10/14/17.
 */
class DetailFragment() : Fragment() {

    var callback: AddPerformanceFragment.SubmitPerformanceCallback? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        view.list_categories.adapter = CategoryAdapter(context)
        view.list_categories.choiceMode = ListView.CHOICE_MODE_SINGLE

        view.btn_submit.setOnClickListener {
            //submitPerformance()
        }

        return view
    }



}