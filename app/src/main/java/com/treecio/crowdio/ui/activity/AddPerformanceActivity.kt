package com.treecio.crowdio.ui.activity

import android.os.Bundle
import com.treecio.crowdio.R

class AddPerformanceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.let {
            setTitle(R.string.add_performance)

        }
    }

}
