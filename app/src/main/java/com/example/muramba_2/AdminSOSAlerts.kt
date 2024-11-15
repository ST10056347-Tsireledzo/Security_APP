import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.muramba_2.R
import com.example.muramba_2.SOSAlert
import com.example.muramba_2.SOSAlertsAdapter
import com.google.firebase.firestore.FirebaseFirestore

class AdminSOSAlerts: AppCompatActivity() {
    private lateinit var sosAlertsRecyclerView: RecyclerView
    private lateinit var sosAlertsAdapter: SOSAlertsAdapter
    private val sosAlertsList = mutableListOf<SOSAlert>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_sosalerts)

        sosAlertsRecyclerView = findViewById(R.id.sosAlertsRecyclerView)
        sosAlertsRecyclerView.layoutManager = LinearLayoutManager(this)
        sosAlertsAdapter = SOSAlertsAdapter(sosAlertsList)
        sosAlertsRecyclerView.adapter = sosAlertsAdapter

        fetchSOSAlerts()
    }

    private fun fetchSOSAlerts() {
        FirebaseFirestore.getInstance().collection("emergency_requests")
            .get()
            .addOnSuccessListener { documents ->
                sosAlertsList.clear()
                for (document in documents) {
                    val sosAlert = document.toObject(SOSAlert::class.java)
                    sosAlertsList.add(sosAlert)
                }
                sosAlertsAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Log.e("AdminSOSAlerts", "Error fetching SOS alerts: ${e.message}")
            }
    }
}

