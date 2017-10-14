package com.treecio.crowdio

import android.Manifest
import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate
import com.facebook.stetho.Stetho
import timber.log.Timber

class CrowdioApp : MultiDexApplication() {

    companion object {
        /**
         * Contains permissions that are recommended and will be prompted for as soon as the user
         * start the app.
         */
        val PERMISSIONS_BASIC = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun onCreate() {
        super.onCreate()

        // Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        // Stetho
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

}
