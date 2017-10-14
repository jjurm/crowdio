package com.treecio.crowdio.network.request

import android.content.Context
import com.treecio.crowdio.model.Performance
import com.treecio.crowdio.network.Endpoints
import com.treecio.crowdio.network.request.abs.ModelPushRequest
import com.treecio.crowdio.network.response.EmptyResponse

class PerformancePushRequest(context: Context, performance: Performance)
    : ModelPushRequest<Performance, EmptyResponse>(EmptyResponse::class.java, context,
        Endpoints.PERFORMANCE_PUSH, performance
)
