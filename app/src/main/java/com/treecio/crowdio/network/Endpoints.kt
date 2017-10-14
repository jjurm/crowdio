package com.treecio.crowdio.network

import com.google.api.client.http.GenericUrl
import com.treecio.crowdio.network.Endpoints.HOST

object Endpoints {

    val HOST = ""

    val PERFORMANCE_PUSH = endpoint("/performance/new")

}

class Endpoint(stringUrl: String) {

    val url = GenericUrl(stringUrl)

}

internal fun endpoint(stringUrl: String) = Endpoint(HOST + stringUrl)
