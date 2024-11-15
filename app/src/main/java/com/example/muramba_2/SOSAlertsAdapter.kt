package com.example.muramba_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SOSAlertsAdapter(private val sosAlerts: List<SOSAlert>) :
    RecyclerView.Adapter<SOSAlertsAdapter.SOSAlertViewHolder>() {

    inner class SOSAlertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userEmailText: TextView = itemView.findViewById(R.id.userEmailText)
        private val locationText: TextView = itemView.findViewById(R.id.locationText)

        fun bind(sosAlert: SOSAlert) {
            userEmailText.text = "User: ${sosAlert.userEmail}"
            locationText.text = "Location: (${sosAlert.latitude}, ${sosAlert.longitude})"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SOSAlertViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sos_alert, parent, false)
        return SOSAlertViewHolder(view)
    }

    override fun onBindViewHolder(holder: SOSAlertViewHolder, position: Int) {
        holder.bind(sosAlerts[position])
    }

    override fun getItemCount(): Int = sosAlerts.size
}
