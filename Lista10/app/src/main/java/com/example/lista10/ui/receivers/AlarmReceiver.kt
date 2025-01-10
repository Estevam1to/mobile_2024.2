package com.example.lista10.ui.receivers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.lista10.R

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Alarme Disparado!", Toast.LENGTH_LONG).show()
        showNotification(context)
    }

    private fun showNotification(context: Context) {
        // Cria um ID único para a notificação
        val notificationId = 1
        val channelId = "alarm_channel"

        // Cria o canal de notificação (necessário para Android 8.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Alarm Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Cria a notificação
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.icon_alarm) // Ícone da notificação
            .setContentTitle("Alarme Disparado!")
            .setContentText("É hora do seu alarme!")
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Importante para visibilidade
            .setAutoCancel(true) // Remove a notificação quando o usuário clicar
            .build()

        // Exibe a notificação
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notification)
    }
}