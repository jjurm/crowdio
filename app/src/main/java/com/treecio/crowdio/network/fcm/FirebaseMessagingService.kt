package com.treecio.crowdio.network.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
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
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.v("FCM: message received: " + remoteMessage.data)

        remoteMessage.data.takeIf { it.isNotEmpty() }?.let { data ->

            when (data[KEY_EVENT]) {

            }

        }

    }

}
