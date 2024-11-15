package com.example.muramba_2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Messages : AppCompatActivity() {
    private lateinit var messagesRecyclerView: RecyclerView
    private lateinit var messagesAdapter: UserQueriesAdapter
    private val queryList = mutableListOf<Query>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        auth = FirebaseAuth.getInstance()
        messagesRecyclerView = findViewById(R.id.messagesRecyclerView)
        messagesRecyclerView.layoutManager = LinearLayoutManager(this)
        messagesAdapter = UserQueriesAdapter(queryList)
        messagesRecyclerView.adapter = messagesAdapter

        fetchUserQueries()
    }

    private fun fetchUserQueries() {
        val userId = auth.currentUser?.uid ?: return
        FirebaseFirestore.getInstance().collection("queries")
            .whereEqualTo("userId", userId)
            .get()
            .addOnSuccessListener { documents ->
                queryList.clear()
                for (document in documents) {
                    val query = document.toObject(Query::class.java).apply {
                        id = document.id
                    }
                    queryList.add(query)
                }
                messagesAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Log.e("Messages", "Error fetching user queries: ${e.message}")
            }
    }
}
