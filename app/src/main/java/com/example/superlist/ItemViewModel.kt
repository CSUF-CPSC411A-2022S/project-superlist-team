package com.example.superlist

import android.app.Application
import androidx.lifecycle.*
import com.example.superlist.database.Item
import com.example.superlist.database.ItemDao
import kotlinx.coroutines.launch

/**
 * IntersectionViewModel used for data binding. Provides a connection to the database
 * for storing and retrieving corresponding values.
 */
class ItemViewModel(
    val database: ItemDao, // Data access object for the Intersection entity
    application: Application) : AndroidViewModel(application) {

    var name = MutableLiveData("")
    var price = MutableLiveData("")

    // Retrieves all Intersection objects from the database
    // Represented as a LiveData<List<Intersection>>
    val itemList = database.getAllItems()

    /**
     * Creates a LiveData<String> that contains information from all Intersection objects.
     * The Transformations.map function takes a LiveData object, performs operations on the
     * object and returns a LiveData-wrapped object.
     */
    var itemString = Transformations.map(itemList) {
        items -> // intersections refer to the underlying data List<Item>
        var result = ""
        // Retrieve each Intersection object from the list
        for (item in items) {
            // Create a string using the Intersection name and price.
            // The intersection string is appended to a longer string with all intersections.
            result += "${item.name} @ ${item.price}\n"
        }
        // Returns the aggregated String that is wrapped by the map function in a LiveData object.
        result
    }
    fun toStringg(): String {
        var result = ""
        Transformations.map(database.getAllItems()) { items -> // intersections refer to the underlying data List<Item>

            // Retrieve each Intersection object from the list
            for (item in items) {
                // Create a string using the Intersection name and price.
                // The intersection string is appended to a longer string with all intersections.
                result += "${item.name} @ ${item.price}\n"
                }
            }
            // Returns the aggregated String that is wrapped by the map function in a LiveData object.
            return result

    }
    /**
     * Inserts the Intersection object into the database.
     */
    fun insert() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Create Intersection object using data stored in the EditText views
            var item = Item()
            item.name = name.value.toString()
            item.price = price.value.toString()

            // Insert data to the database using the insert coroutine.
            database.insert(item)
        }

    }

    /**
     * Deletes all Intersection entities in the database.
     */
    fun clear() {
        // Launch coroutines in the viewModelScope so that the coroutines are automatically
        // canceled when the ViewModel is destroyed.
        viewModelScope.launch {
            // Delete data from the database using the clear coroutine.
            database.clear()
        }
    }
}