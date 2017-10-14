package com.treecio.crowdio.network.request

import android.content.Context
import com.treecio.crowdio.network.Endpoints
import com.treecio.crowdio.network.request.abs.ModelFetchRequest
import com.treecio.crowdio.network.response.PerformancesResponse

class PerformancesFetchRequest(context: Context) : ModelFetchRequest<PerformancesResponse>(
        PerformancesResponse::class.java, context, Endpoints.PERFORMANCES_FETCH)
