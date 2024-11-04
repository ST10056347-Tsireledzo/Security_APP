package com.example.muramba_2

import AdminSOSAlerts
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Admin_Page : AppCompatActivity() {

    private lateinit var viewUsersButton: Button
    private lateinit var viewQueriesButton: Button
    private lateinit var viewSOSAlertsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_page)

        // Set up the toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.adminToolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back) // Optional back icon

        // Initialize buttons
        viewUsersButton = findViewById(R.id.viewUsersButton)
        viewQueriesButton = findViewById(R.id.viewQueriesButton)
        viewSOSAlertsButton = findViewById(R.id.viewSOSAlertsButton)

        // Set up click listeners for each button
        viewUsersButton.setOnClickListener {
            // Display registered users (implement Firebase Firestore retrieval here)
            Toast.makeText(this, "Display registered users list.", Toast.LENGTH_SHORT).show()

            viewQueriesButton.setOnClickListener {
                val intent = Intent(this, AdminQueries::class.java)
                startActivity(intent)
            }
            viewSOSAlertsButton.setOnClickListener {
                val intent = Intent(this, AdminSOSAlerts::class.java)
                startActivity(intent)
            }


        }

    }
        // Optional: Handle the back button on toolbar
        override fun onSupportNavigateUp(): Boolean {
            onBackPressed()
            return true
        }
    }
