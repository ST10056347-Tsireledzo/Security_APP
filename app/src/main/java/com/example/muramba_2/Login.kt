package com.example.muramba_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var link: TextView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.email)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.login)
        link = findViewById(R.id.tv_register)

        auth = FirebaseAuth.getInstance()

        link.setOnClickListener {
            // Navigate to the registration activity
            startActivity(Intent(this, MainActivity::class.java))
        }

        loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Check if the user is the admin
        if (email == "admin@gmail.com" && password == "admin1234") {
            Toast.makeText(this, "Welcome, Admin!", Toast.LENGTH_SHORT).show()
            // Navigate to the AdminActivity
            startActivity(Intent(this, Admin_Page::class.java))
            finish() // Close the login activity
            return
        }

        // Proceed with regular Firebase authentication for normal users
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login successful.", Toast.LENGTH_SHORT).show()
                    // Navigate to the home activity for regular users
                    startActivity(Intent(this, Navigation::class.java))
                    finish() // Close the login activity
                } else {
                    Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { e ->
                // This will catch errors that occur before the sign-in attempt
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
