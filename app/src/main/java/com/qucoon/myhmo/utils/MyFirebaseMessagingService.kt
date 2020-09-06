package com.qucoon.myhmo.utils

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.qucoon.myhmo.R
import com.qucoon.myhmo.views.activity.SplashActivity

class MyFirebaseMessagingService : FirebaseMessagingService(){
    val TAG = "FirebaseMessagingService"

    @SuppressLint("LongLogTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {


        Log.d(TAG, "Dikirim dari: ${remoteMessage.from}")
        Log.d(TAG, "message: ${remoteMessage.notification?.body}")
        Log.d(TAG, "title: ${remoteMessage.notification?.title}")



        if (remoteMessage.notification != null) {
            Log.d(TAG, "igothere")
            showNotification(remoteMessage.notification?.title, remoteMessage.notification?.body)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String) {
        Log.e("NEW_TOKEN", token)
        getSharedPreferences("myhmo-welcome", Context.MODE_PRIVATE).edit()
            .putString("soiikskpcwkdcpdwokpowk-0wk", token).apply()
    }

    private fun showNotification(title: String?, messageBody: String?) {
        try {
            getSharedPreferences("rubies-welcome", Context.MODE_PRIVATE).edit()
                .putString("weiunweiunwiennewinuw", messageBody).apply()
            getSharedPreferences("rubies-welcome", Context.MODE_PRIVATE).edit()
                .putString("weiuowfmomwfwsemsmdpsdnweiunwiennewinuw", title).apply()
            val gcm_rec = Intent("your_action")
            gcm_rec.putExtra("title", title)
            gcm_rec.putExtra("message", messageBody)
            LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(gcm_rec)
        } catch (e: Exception) {
            println(e)
        }
        val notifyIntent = Intent(this, SplashActivity::class.java)
// Set the Activity to start in a new, empty task
        // Set the Activity to start in a new, empty task
        notifyIntent.flags = (Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        notifyIntent.putExtra("title", title)
        notifyIntent.putExtra("message", messageBody)


// Create the PendingIntent

        // Create the PendingIntent
        val notifyPendingIntent = PendingIntent.getActivity(
            this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder =
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.hmologo)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setLights(Color.RED, 3000, 3000)
                .setContentIntent(notifyPendingIntent)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))

//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(0, notificationBuilder.build());
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        //        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(0, notificationBuilder.build());
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Channel human readable title",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManagerr =
                getSystemService(NotificationManager::class.java)
            notificationManagerr.notify(0, notificationBuilder.build())
            notificationManagerr.createNotificationChannel(channel)
            //   notificationManager.createNotificationChannel(channel);
        }

        //    notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
    }
