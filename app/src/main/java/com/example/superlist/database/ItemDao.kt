package com.example.superlist.database

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Data access object for the Intersection entity. The class describes how data is
 * stored, updated, retrieved, or deleted from the database.
 */
@Dao
interface ItemDao {
    // Add an intersection entity to a table in the database.
    // We use suspend to run the function asynchronously (coroutine).
    @Insert
    suspend fun insert(item: Item)

    // Update an intersection entity to a table in the database. Often uses the primary key
    // We use suspend to run the function asynchronously (coroutine).
    @Update
    suspend fun update(item: Item)

    // Custom query for retrieving a single Intersection from a table in the database using
    // its intersection id. We don't use suspend because LiveData objects are already designed
    // to work asynchronous.
    @Query("SELECT * from item_table WHERE itemId = :key")
    fun get(key: Long): LiveData<Item>?

    // Custom query for retrieving all Intersection entities from a table in the database.
    // Data is stored to a List LiveData. We don't use suspend because LiveData objects
    // are already designed to work asynchronously.
    @Query("SELECT * from item_table ORDER BY itemId DESC")
    fun getAllItems(): LiveData<List<Item>>

    // Custom query for deleting all entities on a table in the database
    // We use suspend to run the function asynchronously (coroutine).
    @Query("DELETE from item_table")
    suspend fun clear()


}