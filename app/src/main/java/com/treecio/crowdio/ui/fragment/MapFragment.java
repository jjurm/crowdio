package com.treecio.crowdio.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.treecio.crowdio.R;
import com.treecio.crowdio.model.DataHolder;
import com.treecio.crowdio.model.Performance;
import com.treecio.crowdio.ui.activity.AddPerformanceActivity;
import com.treecio.crowdio.ui.activity.DetailActivity;
import com.treecio.crowdio.util.ExtensionsKt;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;

public class MapFragment extends Fragment
        implements OnMapReadyCallback, DataHolder.PerformancesDataListener,
        GoogleMap.OnCircleClickListener {

    MapView mMapView;
    private GoogleMap map;
    private Collection<Performance> stack;

    private int color;
    private long radius = 0;
    private LatLng coordinates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);

        setupWidgets(rootView);

        return rootView;
    }

    private void setupWidgets(View view) {
        FloatingActionButton btn = view.findViewById(R.id.btn_add_performance);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPerformanceActivity.class);
                getContext().startActivity(intent);
            }
        });
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getContext(), R.string.add_performance, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        DataHolder.INSTANCE.getPerformanceListeners().add(this);
    }

    @Override
    public void onStop() {
        DataHolder.INSTANCE.getPerformanceListeners().remove(this);
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void setData(Collection<Performance> performances) {
        if (!isAdded()) {
            stack = performances;
        } else {
            stack = null;
            for (Performance performance : performances) {
                showPerformance(performance);
            }
        }
    }

    @Override
    public void performanceDataUpdated(@NotNull final HashMap<String, Performance> performances) {
        ExtensionsKt.runOnMainThread(getContext(), new Runnable() {
            @Override
            public void run() {
                setData(performances.values());
            }
        });
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap mMap) {
        map = mMap;

        // For showing a move to my location button
        map.setMyLocationEnabled(true);

        map.setOnCircleClickListener(this);

        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        // Set retro style Google Maps
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.style_json));

        if (stack != null) {
            for (Performance performance : stack) {
                showPerformance(performance);
            }
            stack = null;
        }
    }

    public void showPerformance(Performance performance) {
        Long rating = performance.getRating();
        if (rating == null) {
            rating = 0L;
        }
        double radius = 20 * Math.log(rating + 15) - 20;
        color = ContextCompat.getColor(getContext(), performance.getCategory().getColor());
        coordinates = new LatLng(performance.getLat(), performance.getLng());


        // Extra: popup on the circle
        Circle circle = map.addCircle(new CircleOptions()
                .center(coordinates)
                .radius(radius)
                .strokeColor(color)
                .strokeWidth(5)
                .fillColor(color)
        );
        circle.setTag(performance.getId());
        circle.setClickable(true);
    }

    @Override
    public void onCircleClick(Circle circle) {
        Intent resultIntent = new Intent(getContext(), DetailActivity.class);
        resultIntent.putExtras(DetailActivity.Companion.getArguments((String) circle.getTag()));
        getContext().startActivity(resultIntent);
    }

}
