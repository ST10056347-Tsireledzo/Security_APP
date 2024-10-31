package com.example.muramba_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView

private lateinit var reg: TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        reg = findViewById(R.id.tv_register)

        reg.setOnClickListener {
            // Navigate to the login activity
            startActivity(Intent(this, Navigation::class.java))
        }
    }
}