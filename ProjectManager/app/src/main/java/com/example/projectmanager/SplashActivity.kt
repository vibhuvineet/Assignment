package com.example.projectmanager

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.core.os.HandlerCompat.postDelayed
import kotlinx.android.synthetic.main.activity_splash.*
import androidx.core.os.HandlerCompat.postDelayed

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val typeface: Typeface =
            Typeface.createFromAsset(assets, "carbon bl.ttf")
        tv_app_name.typeface=typeface

        Handler().postDelayed({
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        },2500)
    }
}