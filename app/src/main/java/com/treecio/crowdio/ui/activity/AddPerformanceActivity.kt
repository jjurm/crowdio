package com.treecio.crowdio.ui.activity

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import com.octo.android.robospice.persistence.exception.SpiceException
import com.octo.android.robospice.request.listener.RequestListener
import com.treecio.crowdio.R
import com.treecio.crowdio.model.DataHolder
import com.treecio.crowdio.model.Performance
import com.treecio.crowdio.network.request.PerformancePushRequest
import com.treecio.crowdio.network.response.EmptyResponse
import com.treecio.crowdio.ui.fragment.AddPerformanceFragment
import timber.log.Timber

class AddPerformanceActivity : NetworkActivity(),
        AddPerformanceFragment.SubmitPerformanceCallback {

    companion object {
        const val ADD_PERFORMANCE_FRAGMENT_TAG = "fragment_add_performance"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_fragment)
        supportActionBar?.let {
            setTitle(R.string.add_performance)
        }

        init(savedInstanceState)
    }

    override fun initNew() {
        val fragment = AddPerformanceFragment()
        fragment.callback = this
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment, ADD_PERFORMANCE_FRAGMENT_TAG)
                .commit()
    }

    override fun initRecycled(state: Bundle) {
        val fragment = supportFragmentManager.findFragmentByTag(ADD_PERFORMANCE_FRAGMENT_TAG) as AddPerformanceFragment
        fragment.callback = this
    }

    override fun submit(performance: Performance) {
        val dialog = ProgressDialog.show(this, "", "Submitting...", true)
        dialog.show()

        val request = PerformancePushRequest(this, performance)
        spiceManager.execute(request, object : RequestListener<EmptyResponse> {
            override fun onRequestSuccess(result: EmptyResponse?) {
                dialog.hide()
                performance.id?.let { DataHolder.submittedPerformanes.add(it) }
                Toast.makeText(this@AddPerformanceActivity, "Request successful!", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onRequestFailure(spiceException: SpiceException?) {
                Timber.e(spiceException)
                dialog.hide()
                Toast.makeText(this@AddPerformanceActivity, "Request unsuccessful!", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
