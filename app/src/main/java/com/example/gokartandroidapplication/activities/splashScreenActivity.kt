package com.example.gokartandroidapplication.activities


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.gokartandroidapplication.activities.ui.login.LoginActivity
import com.example.gokartandroidapplication.databinding.ActivitySplashScreenBinding


// Reference: https://www.youtube.com/watch?v=SRHUZd8PH4w
@SuppressLint("CustomSplashScreen")
class splashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)


    }
}