package com.ak.darkmodenotificationapp

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.green
import com.ak.darkmodenotificationapp.databinding.ActivityMainBinding
import com.ak.darkmodenotificationapp.utils.checkNightMode
import com.ak.darkmodenotificationapp.utils.inVisible
import com.ak.darkmodenotificationapp.utils.show

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder

    private val channelId = "i.apps.notification"
    private val description = "Test notification"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        init()
    }

    private fun init() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        handleEvents()
        if (checkNightMode()) {
            binding?.ivLight?.inVisible()
            binding?.ivDark?.show()
            binding?.root?.setBackgroundColor(Color.BLACK)
            binding?.tvContentB?.setTextColor(Color.WHITE)
        } else {
            binding?.ivLight?.show()
            binding?.ivDark?.inVisible()
            binding?.root?.setBackgroundColor(Color.WHITE)
            binding?.tvContentW?.setTextColor(Color.BLACK)
        }
    }

    @SuppressLint("RemoteViewLayout")
    private fun handleEvents() {
        binding?.llModeSwitch?.setOnClickListener {
            if (checkNightMode()) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        binding?.btnNotification?.setOnClickListener {
            val intent=Intent(this,NotificationActivity::class.java)
            val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            val contentView=RemoteViews(packageName,R.layout.activity_notification)
            if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
                notificationChannel=NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.lightColor.green
                notificationChannel.enableVibration(true)
                notificationChannel.enableLights(true)
            }
        }
    }
}

    