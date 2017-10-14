package com.treecio.crowdio.network.request

import android.content.Context
import com.treecio.crowdio.network.Endpoint
import com.treecio.crowdio.network.RequestMethod
import com.treecio.crowdio.network.model.abs.JsonObject

/**
 * Uses GET method to fetch data, expecting a result of class [Response].
 */
open class ModelFetchRequest<Response : JsonObject>(
        clazz: Class<Response>,
        context: Context,
        endpoint: Endpoint
) : BaseRequest<Response>(clazz, context, endpoint, RequestMethod.GET)
