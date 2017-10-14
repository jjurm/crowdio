package com.treecio.crowdio.util

import android.content.Context
import android.os.Handler

fun Context.runOnMainThread(r: Runnable) {
    Handler(getMainLooper()).post(r)
}
