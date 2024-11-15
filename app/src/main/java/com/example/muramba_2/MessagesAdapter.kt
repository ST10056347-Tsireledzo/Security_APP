
package com.example.muramba_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessagesAdapter(private val queries: List<Query>) :
    RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val query = queries[position]
        holder.bind(query)
    }

    override fun getItemCount(): Int = queries.size

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val queryText: TextView = itemView.findViewById(R.id.queryText)
        private val responseText: TextView = itemView.findViewById(R.id.responseText)

        fun bind(query: Query) {
            queryText.text = query.message
            responseText.text = query.response ?: "Awaiting response..."
        }
    }
}
