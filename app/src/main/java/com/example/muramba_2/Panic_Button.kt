// PanicButtonHelper.kt
package com.example.muramba_2

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth

class Panic_Button(private val context: Context) {
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun handleLongPress() {
        requestLocation()
        sendEmergencyNotification()
    }

    private fun requestLocation() {
        try {
            locationManager.requestSingleUpdate(
                LocationManager.NETWORK_PROVIDER,
                object : LocationListener {
                    override fun onLocationChanged(location: Location) {
                        sendLocationToFirebase(location)
                    }
                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
                    override fun onProviderEnabled(provider: String) {}
                    override fun onProviderDisabled(provider: String) {}
                },
                null
            )
        } catch (e: SecurityException) {
            Toast.makeText(context, "Location permission not granted.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendLocationToFirebase(location: Location) {
        val user = auth.currentUser
        val locationData = mapOf(
            "userEmail" to user?.email,
            "userId" to user?.uid,
            "latitude" to location.latitude,
            "longitude" to location.longitude,
            "timestamp" to System.currentTimeMillis()
        )

        firestore.collection("emergency_requests")
            .add(locationData)
            .addOnSuccessListener {
                Toast.makeText(context, "Location sent to emergency services!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to send location.", Toast.LENGTH_SHORT).show()
            }
    }

    private fun sendEmergencyNotification() {
        val notificationData = mapOf(
            "message" to "Emergency services are on the way.",
            "timestamp" to System.currentTimeMillis()
        )

        firestore.collection("notifications")
            .add(notificationData)
            .addOnSuccessListener {
                Toast.makeText(context, "Notification sent.", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Failed to send notification.", Toast.LENGTH_SHORT).show()
            }
    }
}
