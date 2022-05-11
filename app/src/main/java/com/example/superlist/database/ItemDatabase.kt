package com.example.superlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Manages the database that stores the Item table and its entitites.
 * The abstract class should inherit RoomDatabase()
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDatabase: RoomDatabase() {
    // Data access object for the entity.
    abstract val itemDao: ItemDao

    companion object {
        // Holds a reference to the database.
        @Volatile
        private var INSTANCE: ItemDatabase? = null // This class

        // Retrieve an instance of the database tied to the context (your application).
        fun getInstance(context: Context): ItemDatabase {
            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {

                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE

                // The first time the method is called, instance will be `null`, so we should create
                // a new database instance. The next time it is called, the database instance
                // already exists and does not need to be recreated.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDatabase::class.java, // Your database class
                        "sleep_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                // Return database instance; smart cast to be non-null.
                return instance
            }
        }
    }
}