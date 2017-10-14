package com.treecio.crowdio.network

import com.google.api.client.http.GenericUrl
import com.treecio.crowdio.network.Endpoints.HOST

object Endpoints {

    val HOST = "http://54.154.127.121:5000"

    val PERFORMANCES_FETCH = endpoint("/")
    val PERFORMANCE_PUSH = endpoint("/add")

}

class Endpoint(stringUrl: String) {

    val url = GenericUrl(stringUrl)

}

internal fun endpoint(stringUrl: String) = Endpoint(HOST + stringUrl)
