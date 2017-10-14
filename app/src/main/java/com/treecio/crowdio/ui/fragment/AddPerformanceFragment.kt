package com.treecio.crowdio.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import com.treecio.crowdio.R
import com.treecio.crowdio.model.Category
import com.treecio.crowdio.model.Performance
import com.treecio.crowdio.network.request.abs.PerformancePushRequest
import com.treecio.crowdio.ui.adapter.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_add_performance.view.*
import java.util.*



class AddPerformanceFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_performance, container, false)

        view.list_categories.adapter = CategoryAdapter(context)
        view.list_categories.choiceMode = ListView.CHOICE_MODE_SINGLE

        view.btn_submit.setOnClickListener {
            submitPerformance()
        }

        return view
    }

    @SuppressLint("MissingPermission")
    private fun submitPerformance() {

        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()

        val location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false))
        if (location == null) {
            Toast.makeText(context, getString(R.string.unknown_location), Toast.LENGTH_SHORT).show()
            return
        }

        val categoryPosition = view?.list_categories?.selectedItemPosition
        if (categoryPosition == null || categoryPosition == ListView.INVALID_POSITION) {
            Toast.makeText(context, getString(R.string.must_select_category), Toast.LENGTH_SHORT).show()
            return
        }
        val category = Category.values()[categoryPosition]

        val performance = Performance(
                UUID.randomUUID().toString(),
                location.latitude,
                location.longitude,
                Date().time,
                category
        )
        val request = PerformancePushRequest(context, performance)

        //spiceManager.execute(request, lastRequestCacheKey, DurationInMillis.ONE_MINUTE, ListFollowersRequestListener())

    }

}
