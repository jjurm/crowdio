package com.treecio.crowdio.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.treecio.crowdio.R
import com.treecio.crowdio.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_add_performance.view.*

class AddPerformanceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_performance, container, false)

        view.list_categories.adapter = CategoryAdapter(context)

        return view
    }

}
