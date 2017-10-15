package com.treecio.crowdio.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.treecio.crowdio.R
import com.treecio.crowdio.model.Category
import com.treecio.crowdio.model.Performance
import kotlinx.android.synthetic.main.fragment_details.view.*

/**
 * Created by Vytautas on 10/14/17.
 */
class DetailFragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        return view
    }

    fun showPerformance(performance: Performance) {

        val v = view ?: return
        v.categorieTextView.setText(performance.category?.title ?: Category.other.title)

        v.nameTextView.text = "Anonymous"
        v.profile_image.setImageResource(R.drawable.dummy_profile_pic_male1)
        v.locationTextView.text = "Somewhere"
        v.detailTextView.text = performance.description

    }

}
