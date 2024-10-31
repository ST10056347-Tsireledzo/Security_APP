package com.example.muramba_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class Services : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_services)

        val imageView1 = findViewById<ImageView>(R.id.image_view)
        val hiddenTextView1 = findViewById<TextView>(R.id.hiddenTextView)

        imageView1.setOnClickListener {
            // Toggle the visibility of the hidden TextView
            if (hiddenTextView1.visibility == View.GONE) {
                hiddenTextView1.visibility = View.VISIBLE
            } else {
                hiddenTextView1.visibility = View.GONE
            }
        }

        val imageView2 = findViewById<ImageView>(R.id.image_view2)
        val hiddenTextView2 = findViewById<TextView>(R.id.hiddenTextView2)

        imageView2.setOnClickListener {
            // Toggle the visibility of the hidden TextView
            if (hiddenTextView2.visibility == View.GONE) {
                hiddenTextView2.visibility = View.VISIBLE
            } else {
                hiddenTextView2.visibility = View.GONE
            }
        }

        val imageView3 = findViewById<ImageView>(R.id.image_view3)
        val hiddenTextView3 = findViewById<TextView>(R.id.hiddenTextView3)

        imageView3.setOnClickListener {
            // Toggle the visibility of the hidden TextView
            if (hiddenTextView3.visibility == View.GONE) {
                hiddenTextView3.visibility = View.VISIBLE
            } else {
                hiddenTextView3.visibility = View.GONE
            }
        }

        val imageView4 = findViewById<ImageView>(R.id.image_view4)
        val hiddenTextView4 = findViewById<TextView>(R.id.hiddenTextView4)

        imageView4.setOnClickListener {
            // Toggle the visibility of the hidden TextView
            if (hiddenTextView4.visibility == View.GONE) {
                hiddenTextView4.visibility = View.VISIBLE
            } else {
                hiddenTextView4.visibility = View.GONE
            }
        }

        val imageView5 = findViewById<ImageView>(R.id.image_view5)
        val hiddenTextView5 = findViewById<TextView>(R.id.hiddenTextView5)

        imageView5.setOnClickListener {
            // Toggle the visibility of the hidden TextView
            if (hiddenTextView5.visibility == View.GONE) {
                hiddenTextView5.visibility = View.VISIBLE
            } else {
                hiddenTextView5.visibility = View.GONE
            }
        }
    }
}