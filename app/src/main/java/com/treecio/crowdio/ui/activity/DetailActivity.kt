package com.treecio.crowdio.ui.activity

import android.os.Bundle
import com.treecio.crowdio.R
import com.treecio.crowdio.model.Performance
import com.treecio.crowdio.ui.fragment.DetailFragment

class DetailActivity : NetworkActivity() {

    companion object {
        private const val EXTRA_PERFORMANCE_ID = "performance_id"

        fun getArguments(performance: Performance): Bundle {
            val b = Bundle()
            b.putString(EXTRA_PERFORMANCE_ID, performance.id)
            return b
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_fragment)
        init(savedInstanceState)
    }

    override fun initNew() {
        val fragment = DetailFragment()
        supportFragmentManager
                .beginTransaction()
                .add(R.id.content, fragment)
                .commit()
    }

}
