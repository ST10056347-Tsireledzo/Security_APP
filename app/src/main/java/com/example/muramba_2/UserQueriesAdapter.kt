package com.example.muramba_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserQueriesAdapter(
    private val queries: List<Query>
) : RecyclerView.Adapter<UserQueriesAdapter.QueryViewHolder>() {

    inner class QueryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val subjectTextView: TextView = view.findViewById(R.id.subjectTextView)
        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
        val responseTextView: TextView = view.findViewById(R.id.responseTextView)

        fun bind(query: Query) {
            subjectTextView.text = query.subject
            messageTextView.text = query.message
            responseTextView.text = if (query.response.isEmpty()) "No response yet" else query.response
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_query, parent, false)
        return QueryViewHolder(view)
    }

    override fun onBindViewHolder(holder: QueryViewHolder, position: Int) {
        holder.bind(queries[position])
    }

    override fun getItemCount() = queries.size
}
