package com.example.superlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.superlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = this.findNavController(R.id.navigation)
        NavigationUI.setupActionBarWithNavController(this,navController)

        
        val btn_click = findViewById<Button>(R.id.add)
        val delete_button = findViewById<Button>(R.id.delete)
        val clear_button = findViewById<Button>(R.id.clear)

        btn_click.setOnClickListener{
            Toast.makeText(this@MainActivity, "Adding", Toast.LENGTH_SHORT).show()
        }
        delete_button.setOnClickListener {
            Toast.makeText(this@MainActivity, "Deleting", Toast.LENGTH_SHORT).show()
        }
        clear_button.setOnClickListener {
            Toast.makeText(this@MainActivity, "Clearing", Toast.LENGTH_SHORT).show()
        }
    }
}
