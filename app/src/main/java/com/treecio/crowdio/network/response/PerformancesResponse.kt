package com.treecio.crowdio.network.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.api.client.util.Key
import com.treecio.crowdio.model.Performance
import com.treecio.crowdio.network.model.abs.JsonObject

class PerformancesResponse(

        @Key("performances")
        @JsonProperty("performances")
        var performances: List<Performance>? = null

) : JsonObject
