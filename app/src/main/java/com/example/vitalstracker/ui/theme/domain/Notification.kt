package com.example.vitalstracker.ui.theme.domain

import android.Manifest
import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.vitalstracker.MainActivity

class Notification(
    context: Context,
    params: WorkerParameters
    ): Worker(context, params) {

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun doWork(): Result {
        val channelId = "vitals_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                "Vitals Reminder",
                NotificationManager.IMPORTANCE_HIGH
            )
            var manager = applicationContext
                .getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(applicationContext,channelId)
            .setSmallIcon(R.drawable.ic_dialog_info)
            .setContentTitle("Time to Log Your Vitals")
            .setContentTitle("Stay On Top Of Your Health. Please Update Your Vitals now")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        NotificationManagerCompat.from(applicationContext).notify(1, notification)
        return  Result.success()
    }


}