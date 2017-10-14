package com.treecio.crowdio.ui.activity

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.octo.android.robospice.persistence.exception.SpiceException
import com.octo.android.robospice.request.listener.RequestListener
import com.treecio.crowdio.CrowdioApp
import com.treecio.crowdio.R
import com.treecio.crowdio.model.DataHolder
import com.treecio.crowdio.network.request.PerformancesFetchRequest
import com.treecio.crowdio.network.response.PerformancesResponse
import com.treecio.crowdio.permission.PermissionCallback
import com.treecio.crowdio.permission.PermissionFlow
import com.treecio.crowdio.permission.PermissionFlowResult
import com.treecio.crowdio.ui.fragment.MapFragment
import timber.log.Timber

class MainActivity : NetworkActivity(), PermissionCallback {

    companion object {
        const val MAP_FRAGMENT_TAG = "fragment_map"
    }

    private var permissionsChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (!permissionsChecked) {
            permissionsChecked = true
            PermissionFlow.builder(this, CrowdioApp.PERMISSIONS_BASIC)
                    .interactive(R.string.rationale_crucial_permissions)
                    .callback(this)
                    .flow()
        }
    }

    override fun onPermissionGranted(context: Context, requestId: Int, args: Bundle, result: PermissionFlowResult) {
        val fragment = MapFragment()
        fragment.setData(DataHolder.performances.values)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment, MAP_FRAGMENT_TAG)
                .commit()
        fetchData()
    }

    override fun onPermissionDenied(context: Context, requestId: Int, args: Bundle, result: PermissionFlowResult) {
        Toast.makeText(this, R.string.app_cant_continue_without_permissions, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun fetchData() {
        val request = PerformancesFetchRequest(this)
        spiceManager.execute(request, object : RequestListener<PerformancesResponse> {
            override fun onRequestSuccess(result: PerformancesResponse?) {
                Timber.d("Got new data" + if (result == null) " (is null)" else "")
                result ?: return

                val fragment = supportFragmentManager.findFragmentByTag(MAP_FRAGMENT_TAG) as MapFragment
                fragment.setData(DataHolder.performances.values)
            }
            override fun onRequestFailure(spiceException: SpiceException?) {
                Timber.e(spiceException)
            }
        })
    }

}
