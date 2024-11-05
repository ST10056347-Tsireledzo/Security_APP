package com.example.muramba_2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.muramba_2.R
import java.text.SimpleDateFormat
import java.util.*

class Access_Control : AppCompatActivity() {

    private lateinit var accessCodeText: TextView
    private lateinit var timestampText: TextView
    private lateinit var cardView: CardView
    private lateinit var generateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_control)

        // Initialize UI elements
        accessCodeText = findViewById(R.id.accessCodeText)
        timestampText = findViewById(R.id.timestampText)
        cardView = findViewById(R.id.cardView)
        generateButton = findViewById(R.id.generateButton)

        // Hide CardView initially
        cardView.visibility = View.GONE

        // Generate code and display it on button click
        generateButton.setOnClickListener {
            generateAccessCode()
        }
    }

    private fun generateAccessCode() {
        // Generate a random 6-digit code
        val code = (100000..999999).random()
        val message = "Guest access code is \"$code\""

        // Get the current timestamp
        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

        // Display the code and timestamp in CardView
        accessCodeText.text = message
        timestampText.text = "Generated at: $timestamp"
        cardView.visibility = View.VISIBLE

        // Optional: Toast to confirm code generation
        Toast.makeText(this, "Access code generated!", Toast.LENGTH_SHORT).show()
    }
}
