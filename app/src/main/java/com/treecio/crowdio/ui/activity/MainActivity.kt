package com.treecio.crowdio.ui.activity

import android.os.Bundle
import com.treecio.crowdio.R
import com.treecio.crowdio.ui.fragment.AddPerformanceFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initNew() {
        super.initNew()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, AddPerformanceFragment())
                .commit()
    }

}
