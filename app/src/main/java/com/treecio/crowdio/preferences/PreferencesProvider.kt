package com.treecio.crowdio.preferences

import android.content.Context
import android.support.v4.app.Fragment
import com.treecio.crowdio.preferences.PreferencesProvider.Companion.GLOBAL

open class Preferences(context: Context, key: String) {

    internal val prefs by lazy { context.getSharedPreferences(key, Context.MODE_PRIVATE) }

}

class PreferencesProvider(private val context: Context) {

    constructor(fragment: Fragment) : this(fragment.context)

    companion object {
        internal const val GLOBAL = "global"
    }

    val global by lazy { GlobalPreferences(context) }

}

class GlobalPreferences(context: Context) : Preferences(context, GLOBAL) {



}
