package com.example.muramba_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.muramba_2.databinding.ActivityNavigationBinding

class Navigation : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding // Declare binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homefragment = Home()


        // Initialize with the home fragment
        setCurrentFragment(homefragment)

        // Use view binding to access the BottomNavigationView
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> setCurrentFragment(homefragment)

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}
