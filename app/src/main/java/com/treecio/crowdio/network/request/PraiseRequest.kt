package com.treecio.crowdio.network.request

import android.content.Context
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.api.client.util.Key
import com.treecio.crowdio.model.Performance
import com.treecio.crowdio.network.Endpoints
import com.treecio.crowdio.network.model.abs.JsonModel
import com.treecio.crowdio.network.request.abs.ModelPushRequest
import com.treecio.crowdio.network.response.EmptyResponse

class PraiseRequest(context: Context, performance: Performance)
    : ModelPushRequest<PraiseRequest.Payload, EmptyResponse>(EmptyResponse::class.java, context,
        Endpoints.PRAISE, Payload(performance.id)) {

    class Payload(
            @Key("id")
            @JsonProperty("id")
            var id: String? = null
    ) : JsonModel()

}
