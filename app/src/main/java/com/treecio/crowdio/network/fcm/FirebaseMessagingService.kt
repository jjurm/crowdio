package com.treecio.crowdio.network.fcm

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.treecio.crowdio.model.DataHolder
import com.treecio.crowdio.model.Performance
import com.treecio.crowdio.preferences.PreferencesProvider
import timber.log.Timber


/**
 * This service receives and handles notifications from Firebase.
 */
class FirebaseMessagingService : FirebaseMessagingService() {

    val prefs by lazy { PreferencesProvider(this) }

    companion object {
        private const val KEY_EVENT = "event"
        private const val KEY_DATA = "data"
        private const val EVENT_NEW_PERFORMANCE = "new_performance"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.v("FCM: message received: " + remoteMessage.data)

        remoteMessage.data.takeIf { it.isNotEmpty() }?.let { data ->

            when (data[KEY_EVENT]) {
                EVENT_NEW_PERFORMANCE -> newPerformance(data)
            }

        }

    }

    private fun newPerformance(data: Map<String, String>) {
        // get json call from firebase
        val mapper = ObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val performance = mapper.readValue(data[KEY_DATA], Performance::class.java)
        DataHolder.updatePerformance(performance)
    }

}
