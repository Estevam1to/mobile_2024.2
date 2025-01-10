package com.example.lista10.utils

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.AlarmManagerCompat
import com.example.lista10.ui.receivers.AlarmReceiver
import java.util.Calendar

object UtilsFunctions {
    fun setAlarm(context: Context, hour: Int, minute: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !AlarmManagerCompat.canScheduleExactAlarms(
                alarmManager
            )
        ) {
            requestExactAlarmPermission(context)
            Toast.makeText(
                context,
                "Permissão necessária para configurar alarmes exatos.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            set(Calendar.DAY_OF_YEAR, get(Calendar.DAY_OF_YEAR))
            set(Calendar.YEAR, get(Calendar.YEAR))

            Log.d("Calendar", Calendar.DAY_OF_YEAR.toString())
            Log.d("Calendar", Calendar.YEAR.toString())

            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)

        Toast.makeText(
            context,
            "Alarme configurado para ${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH) + 1}-${
                calendar.get(
                    Calendar.DAY_OF_MONTH
                )
            } às %02d:%02d".format(hour, minute),
            Toast.LENGTH_SHORT
        ).show()
    }


    private fun requestExactAlarmPermission(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            context.startActivity(intent)
        }
    }

}