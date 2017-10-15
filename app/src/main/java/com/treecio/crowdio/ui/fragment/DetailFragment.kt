package com.treecio.crowdio.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.treecio.crowdio.R
import com.treecio.crowdio.model.Performance

/**
 * Created by Vytautas on 10/14/17.
 */
class DetailFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        return view
    }

    private fun showPerformance(performance: Performance) {

    }

}
