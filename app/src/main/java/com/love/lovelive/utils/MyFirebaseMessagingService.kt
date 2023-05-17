package com.love.lovelive.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.love.lovelive.ui.activities.MainActivity
import com.love.lovelive.R
import timber.log.Timber
import java.util.*


class MyFirebaseMessagingService : FirebaseMessagingService(){

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Timber.d("From:Notification=> " + remoteMessage.from)
        Timber.d("Message data payload: " + remoteMessage.data)
        Timber.d("Message data payload: " + Gson().toJson(remoteMessage))
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Timber.d("Message data payload: " + remoteMessage.data)

            if (
// Check if data needs to be processed by long running job
 true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleJob()
            } else {
                // Handle message within 10 seconds
                handleNow()
            }
        }

        val notNo = generateRandom()

        val resultPendingIntent =
            PendingIntent.getActivity(
                this,
                notNo,
                Intent(this, MainActivity::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

        showNotification(this, resultPendingIntent, remoteMessage, notNo)
        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Timber.d("Message Notification Body: " + it.body)
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    // [START on_new_token]
//*
//     * Called if the FCM registration token is updated. This may occur if the security of
//     * the previous token had been compromised. Note that this is called when the
//     * FCM registration token is initially generated so this is where you would retrieve the token.


    override fun onNewToken(token: String) {
        Timber.d("Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token)
    }
    // [END on_new_token]

    private fun scheduleJob() {
        // [START dispatch_job]
        val work = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .build()
        WorkManager.getInstance(this)
            .beginWith(work)
            .enqueue()
        // [END dispatch_job]
    }

    private fun handleNow() {
        Timber.d("Short lived task is done.")
    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Timber.d("sendRegistrationTokenToServer($token)")
    }

    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }

    internal class MyWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {
        override fun doWork(): Result {
            // TODO(developer): add long running task here.
            return Result.success()
        }
    }
    private fun generateRandom(): Int {
        val random = Random()
        return random.nextInt(9999 - 1000) + 1000
    }

    private fun showNotification(
        context: Context,
        pendingIntent: PendingIntent?,
        notification: RemoteMessage,
        NotificationId: Int
    ) {
        val notificationBuilder = NotificationCompat.Builder(context, "share_me")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.rectangle)
            .setContentTitle(notification.notification?.title)
            .setContentText(notification.notification?.body)
            .setContentIntent(pendingIntent)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setColor(ContextCompat.getColor(context, R.color.black))
            .setAutoCancel(true)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("share_me", "share_me", NotificationManager.IMPORTANCE_HIGH)
            channel.importance = NotificationManager.IMPORTANCE_HIGH
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(NotificationId, notificationBuilder.build())
    }

}
