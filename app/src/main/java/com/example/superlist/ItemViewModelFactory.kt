package com.example.superlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.superlist.database.ItemDao

/**
 * Generates an ItemViewModel tied to the database.
 */
class ItemViewModelFactory(
    private val dataSource: ItemDao, // Data access object
    private val application: Application): ViewModelProvider.Factory {

    /**
     * Creates the ItemViewModel
     */
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) { // ViewModel class
            return ItemViewModel(dataSource, application) as T // Call ViewModel constructor
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}