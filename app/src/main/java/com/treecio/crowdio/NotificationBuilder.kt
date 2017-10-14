package com.treecio.crowdio

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.app.TaskStackBuilder
import android.support.v4.content.ContextCompat
import com.treecio.crowdio.model.Performance
import com.treecio.crowdio.ui.activity.DetailActivity
import com.treecio.crowdio.ui.activity.MainActivity

/**
 * Outsources the notification building process.
 */
class NotificationBuilder(private val context: Context) {
    companion object {

        @DrawableRes
        private val SMALL_ICON = R.drawable.icon_music

        // this will be used as ID for notifications and incremented
        private var id = 1
    }

    private val notificationManager = NotificationManagerCompat.from(context)

    private fun baseBuilder() = NotificationCompat.Builder(context)
            .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .setSmallIcon(SMALL_ICON)

    fun performanceNearby(performance: Performance) {
        val category = performance.category ?: return
        val performanceTitle = context.getString(category.title)

        val builder = baseBuilder()
                .setContentTitle(context.getString(R.string.performance_nearby, performanceTitle))
                .setContentText(context.getString(R.string.you_might_be_interested))
                .setAutoCancel(true)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), category.icon))
                .setContentIntent(getPerformanceDetailIntent(performance))
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_VIBRATE or Notification.DEFAULT_LIGHTS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(Notification.CATEGORY_EMAIL)
                    .setVisibility(Notification.VISIBILITY_PRIVATE)
        }
        notificationManager.notify(nextId(), builder.build())
    }

    /**
     * Returns the next new ID that can be used for identifying a notification, then increments the
     * stored value.
     *
     * @return id
     */
    private fun nextId() = id++

    private fun getPerformanceDetailIntent(performance: Performance): PendingIntent {
        val resultIntent = Intent(context, DetailActivity::class.java)
        resultIntent.putExtras(DetailActivity.getArguments(performance))

        // Create a stack that navigates user from the target activity up through the parent stack
        // Notification --click--> DetailActivity --back--> MainActivity --back--> Home
        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(resultIntent)
        // Get a PendingIntent containing the entire back stack
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }

}
