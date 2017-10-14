package com.treecio.crowdio.ui.activity

import android.os.Bundle
import com.treecio.crowdio.R
import com.treecio.crowdio.ui.fragment.AddPerformanceFragment

class AddPerformanceActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_performance)
        supportActionBar?.let {
            setTitle(R.string.add_performance)
        }

        init(savedInstanceState)
    }

    override fun initNew() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, AddPerformanceFragment())
                .commit()
    }

}
