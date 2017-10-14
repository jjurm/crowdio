package com.treecio.crowdio.ui.activity

import com.octo.android.robospice.SpiceManager

abstract class NetworkActivity : BaseActivity() {

    val spiceManager = SpiceManager(com.octo.android.robospice.UncachedSpiceService::class.java)

    override fun onStart() {
        super.onStart()
        spiceManager.start(this)
    }

    override fun onStop() {
        spiceManager.shouldStop()
        super.onStop()
    }

}
