package com.example.superlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.superlist.database.ItemDatabase
import com.example.superlist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        val navController = this.findNavController(R.id.nav_host)
        NavigationUI.setupActionBarWithNavController(this,navController)

        // Get reference to this application
        val application = requireNotNull(this).application

        // Retrieve Intersection data access object.
        val dataSource = ItemDatabase.getInstance(application).itemDao

        // Create a factory that generates IntersectionViewModels connected to the database.
        val viewModelFactory = ItemViewModelFactory(dataSource, application)

        // Generate an IntersectionViewModel using the factory.
        val itemViewModel =
            ViewModelProvider(
                this, viewModelFactory).get(ItemViewModel::class.java)

        // Connect the IntersectionViewModel with the variable in the layout
        binding.itemViewModel = itemViewModel
        // Assign the lifecycle owner to the activity so it manages the data accordingly.
        binding.lifecycleOwner = this

        val toast = Toast.makeText(applicationContext, itemViewModel.toStringg(), Toast.LENGTH_LONG)
        toast.show()
    }
}