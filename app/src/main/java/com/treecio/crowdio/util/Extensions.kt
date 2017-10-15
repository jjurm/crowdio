package com.treecio.crowdio.util

import android.content.Context
import android.os.Handler

fun Context.runOnMainThread(r: Runnable) {
    Handler(getMainLooper()).post(r)
}

fun <A, R> withNonNull(a: A?, block: (A) -> R): R? {
    return if (a != null) block.invoke(a) else null
}

fun <A, B, R> withNonNull(a: A?, b: B?, block: (A, B) -> R): R? {
    return if (a != null && b != null) block.invoke(a, b) else null
}
