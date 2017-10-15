package com.treecio.crowdio.ui.activity

import android.os.Bundle
import com.octo.android.robospice.persistence.exception.SpiceException
import com.octo.android.robospice.request.listener.RequestListener
import com.treecio.crowdio.R
import com.treecio.crowdio.model.DataHolder
import com.treecio.crowdio.model.Performance
import com.treecio.crowdio.network.request.PerformancesFetchRequest
import com.treecio.crowdio.network.response.PerformancesResponse
import com.treecio.crowdio.ui.fragment.DetailFragment

class DetailActivity : NetworkActivity(), DataHolder.PerformancesDataListener {
    companion object {

        private const val TAG_FRAGMENT_DETAILS = "fragment_details"
        private const val EXTRA_PERFORMANCE_ID = "performance_id"
        fun getArguments(performance: Performance) = getArguments(performance.id)

        fun getArguments(id: String?): Bundle {
            val b = Bundle()
            b.putString(EXTRA_PERFORMANCE_ID, id)
            return b
        }

    }

    val id: String? by lazy { intent.getStringExtra(EXTRA_PERFORMANCE_ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_fragment)

        init(savedInstanceState)
    }

    override fun initNew() {
        val fragment = DetailFragment()
        supportFragmentManager
                .beginTransaction()
                .add(R.id.content, fragment, TAG_FRAGMENT_DETAILS)
                .commit()
    }

    override fun onStart() {
        super.onStart()

        showPerformance()
        DataHolder.performanceListeners.add(this)

        val request = PerformancesFetchRequest(this)
        spiceManager.execute(request, object : RequestListener<PerformancesResponse> {
            override fun onRequestSuccess(result: PerformancesResponse?) {
            }

            override fun onRequestFailure(spiceException: SpiceException?) {
            }
        })
    }

    override fun onStop() {
        DataHolder.performanceListeners.remove(this)
        super.onStop()
    }

    private fun showPerformance() {
        val performance = DataHolder.performances[id] ?: return
        val fragment = supportFragmentManager.findFragmentByTag(TAG_FRAGMENT_DETAILS) as DetailFragment
        runOnUiThread {
            supportActionBar?.let {
                val catTitle = performance.category?.title?.let { getString(it) } ?: ""
                title = getString(R.string.placeholder_performance, catTitle).trim().capitalize()
            }
            fragment.showPerformance(performance)
        }
    }

    override fun performanceDataUpdated(performances: HashMap<String, Performance>) {
        showPerformance()
    }

}
