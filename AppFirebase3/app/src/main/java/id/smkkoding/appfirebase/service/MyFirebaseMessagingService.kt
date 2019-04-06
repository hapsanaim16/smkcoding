package id.smkkoding.appfirebase.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import id.smkkoding.appfirebase.R

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage != null) {
            sendNotification(remoteMessage)
        }
    }


private fun sendNotification (remoteMessage: RemoteMessage) {
    val title = remoteMessage.data["title"]
    val content = remoteMessage.data["content"]
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val NOTIFICATION_CHANNEL_ID = "SMKcoding"
    @RequiresApi(Build.VERSION_CODES.O)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notifikasiku", NotificationManager.IMPORTANCE_MAX)
        //config
        notificationChannel.description = "Notifikasiku Channel"
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = (Color.RED)
        notificationChannel.vibrationPattern = longArrayOf(0,1000,500,500)
        notificationChannel.enableVibration(true)
        notificationManager.createNotificationChannel(notificationChannel)
    }
    val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
    notificationBuilder.setAutoCancel(true)
        .setDefaults(Notification.DEFAULT_ALL)
        .setWhen(System.currentTimeMillis())
        .setSmallIcon(R.drawable.notification_icon_background)
        .setContentTitle(title)
        .setContentText(content)
    notificationManager.notify(1,notificationBuilder.build())
}
}