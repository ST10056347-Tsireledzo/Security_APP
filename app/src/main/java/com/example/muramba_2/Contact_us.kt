package com.example.muramba_2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Contact_us : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Find views
        val subjectSpinner = findViewById<Spinner>(R.id.subjectSpinner)
        val queryMessage = findViewById<EditText>(R.id.queryMessage)
        val sendQueryButton = findViewById<Button>(R.id.sendQueryButton)

        // Set up dropdown
        ArrayAdapter.createFromResource(
            this,
            R.array.company_services,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            subjectSpinner.adapter = adapter
        }

        // Handle send button
        sendQueryButton.setOnClickListener {
            sendQuery(subjectSpinner, queryMessage)
        }
    }

    private fun sendQuery(subjectSpinner: Spinner, queryMessage: EditText) {
        val userEmail = auth.currentUser?.email ?: "No Email"
        val subject = subjectSpinner.selectedItem.toString()
        val message = queryMessage.text.toString().trim()

        if (message.isNotEmpty()) {
            val queryData = mapOf(
                "email" to userEmail,
                "subject" to subject,
                "message" to message,
                "timestamp" to System.currentTimeMillis()
            )

            firestore.collection("queries")
                .add(queryData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Query sent successfully!", Toast.LENGTH_SHORT).show()
                    queryMessage.text.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to send query. Please try again.", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "Please enter a message.", Toast.LENGTH_SHORT).show()
        }
    }
}
