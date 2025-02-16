package com.example.mobile_computing.util

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.mobile_computing.R
import com.example.mobile_computing.ui.MainActivity
import kotlin.random.Random

class PickupDetectorService : Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastZ: Float = 0.0f
    private var lastTime: Long = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
        startForegroundService()
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            val currentTime = System.currentTimeMillis()
            if ((currentTime - lastTime) > 100) {
                lastTime = currentTime
                val z = it.values[2] // Z-axis detects vertical movement
                if (z - lastZ > 5 ) { // Detect upward movement
                    showSimpleNotification() // Trigger notification
                }
                lastZ = z
            }
        }
    }

    private fun showSimpleNotification() {

         val notificationManager = this.getSystemService(NotificationManager::class.java)
         val notificationChannelID = "notification_channel_id"

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, notificationChannelID)
            .setContentTitle("TODO APPLICATION AWAITS")
            .setContentText("You picked up the phone!")
            .setSmallIcon(R.drawable.honda_logo)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()  // finalizes the creation

        notificationManager.notify(Random.nextInt(), notification)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ForegroundServiceType")
    private fun startForegroundService() {
        val channelId = "PickupDetectorChannel"
        val channel = NotificationChannel(
            channelId, "Pickup Detector", NotificationManager.IMPORTANCE_LOW
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        val notification = Notification.Builder(this, channelId)
            .setContentTitle("Pickup Detector Running")
            .setContentText("Listening for Pickup events...")
            .setSmallIcon(android.R.drawable.ic_menu_compass)
            .build()

        startForeground(1, notification)
    }



}