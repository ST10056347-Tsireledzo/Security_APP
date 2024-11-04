package com.example.muramba_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdminQueriesAdapter(
    private val queries: List<Query>,
    private val onResponseSubmit: (Query, String) -> Unit
) : RecyclerView.Adapter<AdminQueriesAdapter.QueryViewHolder>() {

    inner class QueryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subjectTextView: TextView = view.findViewById(R.id.subjectTextView)
        val emailTextView: TextView = view.findViewById(R.id.emailTextView)
        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
        val responseEditText: EditText = view.findViewById(R.id.responseEditText)
        val sendResponseButton: Button = view.findViewById(R.id.sendResponseButton)

        fun bind(query: Query) {
            subjectTextView.text = query.subject
            emailTextView.text = query.email
            messageTextView.text = query.message
            sendResponseButton.setOnClickListener {
                val responseText = responseEditText.text.toString()
                onResponseSubmit(query, responseText)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_admin_query, parent, false)
        return QueryViewHolder(view)
    }

    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {
        holder.bind(queries[position])
    }

    override fun getItemCount() = queries.size
}
