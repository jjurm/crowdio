package com.treecio.crowdio.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.api.client.util.Key
import com.treecio.crowdio.network.model.abs.JsonModel

data class Performance(

        @Key("id")
        @JsonProperty("id")
        var id: String? = null,

        @Key("lat")
        @JsonProperty("lat")
        var lat: Double? = null,
        @Key("lng")
        @JsonProperty("lng")
        var lng: Double? = null,

        @Key("timestamp_start")
        @JsonProperty("timestamp_start")
        var timestampStart: Long? = null,

        @Key("category")
        @JsonProperty("category")
        var category: Category? = null,
        @Key("description")
        @JsonProperty("description")
        var description: String? = null,

        @Key("rating")
        @JsonProperty("rating")
        var rating: Long? = null

) : JsonModel()
