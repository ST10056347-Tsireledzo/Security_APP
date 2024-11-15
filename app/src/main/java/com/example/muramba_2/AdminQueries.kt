package com.example.muramba_2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class AdminQueries : AppCompatActivity() {
    private lateinit var queriesRecyclerView: RecyclerView
    private lateinit var queriesAdapter: AdminQueriesAdapter
    private val queryList = mutableListOf<Query>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_queries)

        // Initialize RecyclerView and Adapter
        queriesRecyclerView = findViewById(R.id.queriesRecyclerView)
        queriesRecyclerView.layoutManager = LinearLayoutManager(this)

        // Assign queryList directly to the adapter
        queriesAdapter = AdminQueriesAdapter(queryList) { query, response ->
            updateQueryResponse(query.id, response)
        }
        queriesRecyclerView.adapter = queriesAdapter

        // Fetch all queries
        fetchAllUserQueries()
    }

    private fun fetchAllUserQueries() {
        FirebaseFirestore.getInstance().collection("queries")
            .whereEqualTo("response", "") // Fetch only queries without a response
            .get()
            .addOnSuccessListener { documents ->
                queryList.clear()
                for (document in documents) {
                    val query = document.toObject(Query::class.java).apply {
                        id = document.id
                    }
                    queryList.add(query)
                }
                queriesAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Log.e("AdminQueries", "Error fetching queries: ${e.message}")
            }
    }

    private fun updateQueryResponse(queryId: String, response: String) {
        FirebaseFirestore.getInstance().collection("queries").document(queryId)
            .update("response", response)
            .addOnSuccessListener {
                Log.d("AdminQueries", "Response updated for $queryId")

                // Remove the responded query from the list
                queryList.removeAll { it.id == queryId }
                queriesAdapter.notifyDataSetChanged() // Refresh the view
            }
            .addOnFailureListener { e ->
                Log.e("AdminQueries", "Error updating response: ${e.message}")
            }
    }

}

