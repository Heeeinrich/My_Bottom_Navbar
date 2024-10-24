package com.example.mybottomnavbar

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mybottomnavbar.databinding.ActivityMainBinding
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Set the toolbar as the ActionBar
        setSupportActionBar(binding.toolbar) // This line sets the Toolbar as the ActionBar

        with(binding) {
            val navController = findNavController(R.id.nav_host_fragemnt)
            bottomNavigationView.setupWithNavController(navController)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                Toast.makeText(this, "You Have Successfully Logged Out", Toast.LENGTH_SHORT).show()
                finishAffinity() // Exit the application
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Method to show DatePickerDialog

}
