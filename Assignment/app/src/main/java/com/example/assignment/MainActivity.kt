package com.example.assignment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.app.NotificationCompat
import com.example.assignment.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOne.setOnClickListener{
            if(binding.etText.text!!.isEmpty()){
                Toast.makeText(this@MainActivity,"Please enter a text",Toast.LENGTH_SHORT).show()
            }else{
            Toast.makeText(this,"${binding.etText.text}",Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnTwo.setOnClickListener{
            if(binding.etText.text!!.isEmpty()){
                Toast.makeText(this@MainActivity,"Please enter a text",Toast.LENGTH_SHORT).show()
            }else{
            showNotification()
            }
        }
        binding.btnThree.setOnClickListener{
            if(binding.etText.text!!.isEmpty()){
                Toast.makeText(this@MainActivity,"Please enter a text",Toast.LENGTH_SHORT).show()
            }else{
            val intent = Intent(this, PopUpWindowActivity::class.java)
            intent.putExtra("popuptitle", "Modal PopUp")
            intent.putExtra("popuptext", "${binding.etText.text}")
            intent.putExtra("popupbtn", "OK")
            intent.putExtra("darkstatusbar", false)
            startActivity(intent)
            }
        }
        binding.btnFour.setOnClickListener{
            if(binding.etText.text!!.isEmpty()){
                Toast.makeText(this@MainActivity,"Please enter a text",Toast.LENGTH_SHORT).show()
            }else{
            val editText= binding.etText
            editText.setText(getString(R.string.change_text_by_button))
            }
        }
    }

    private fun showNotification() {
        val channelID="10000"
        val contentView = RemoteViews(packageName,R.layout.custom_notification_layout)

        //start this (MainActivity) on by tapping notification
        val mainIntent = Intent(this,MainActivity::class.java)
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val mainPIntent=PendingIntent.getActivity(this,0,mainIntent,PendingIntent.FLAG_ONE_SHOT)


        contentView.setTextViewText(R.id.tvTitle,"Notification")
        contentView.setTextViewText(R.id.tvDesc,"${binding.etText.text}")
        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder= NotificationCompat.Builder(applicationContext,channelID)
            .setSmallIcon(R.drawable.ic_white_notifications_24dp)
            //.setContentTitle("Notifications")
            //.setContentText("${binding.etText.text}")
            .setCustomContentView(contentView)

        builder.setContent(contentView)

        //dismiss on tap
        builder.setAutoCancel(true)
        //start intent on notification tap(MainActivity)
        builder.setContentIntent(mainPIntent)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelID,"custom Notification",NotificationManager.IMPORTANCE_DEFAULT)

            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
            builder.setChannelId(channelID)
        }
        val notification = builder.build()

        notificationManager.notify(1000,notification)
    }
}

