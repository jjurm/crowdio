package com.treecio.crowdio.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.treecio.crowdio.R
import com.treecio.crowdio.model.Category
import com.treecio.crowdio.model.Performance
import com.treecio.crowdio.util.withNonNull
import kotlinx.android.synthetic.main.fragment_details.view.*
import java.util.*


/**
 * Created by Vytautas on 10/14/17.
 */
class DetailFragment() : Fragment(), OnMapReadyCallback {

    internal lateinit var mMapView: MapView
    private var map: GoogleMap? = null
    var location: LatLng? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        mMapView = view.findViewById(R.id.mapView)
        mMapView.isClickable = false
        mMapView.onCreate(savedInstanceState)

        mMapView.onResume() // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(activity.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mMapView.getMapAsync(this)

        mMapView.setOnClickListener {
            withNonNull(location) { location ->
                val uri = String.format(Locale.ENGLISH, "geo:%f,%f",
                        location.latitude, location.latitude)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                context.startActivity(intent)
            }
        }

        return view
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map ?: return

        withNonNull(location) { location ->
            map.addMarker(MarkerOptions()
                    .position(location)
            )

            val cameraPosition = CameraPosition.Builder()
                    .target(LatLng(location.latitude, location.longitude))      // Sets the center of the map to location user
                    .zoom(17f)                   // Sets the zoom
                    .tilt(40f)                   // Sets the tilt of the camera to 30 degrees
                    .build()                   // Creates a CameraPosition from the builder
            map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }

        this.map = map
    }

    fun showPerformance(performance: Performance) {

        withNonNull(performance.lat, performance.lng) { lat, lng ->
            location = LatLng(lat, lng)
        }

        val v = view ?: return
        v.categorieTextView.setText(performance.category?.title ?: Category.other.title)

        v.nameTextView.text = "Anonymous"
        v.profile_image.setImageResource(performance.category?.coloredIcon ?: Category.other.icon)
        v.locationTextView.text = "Somewhere"
        v.detailTextView.text = performance.description

    }

}
