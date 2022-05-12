package com.example.superlist.database;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.superlist.Nutrition


@Entity(tableName = "item_table")
data class Item (
    // Defines the table's primary key. Primary keys are unique values that can be autogenerated.
    // They are used to differentiate one entity from another.
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,


    @ColumnInfo()
    var price: Double = 0.0,

    // Non-rprimary key column. Data type is specified in the property.
    @ColumnInfo()
    var searchName: String = "",

    @ColumnInfo()
    var productName: String = "",

    @ColumnInfo()
    var thumbnail: String = "",

    @ColumnInfo()
    var calories: String = "",
    @ColumnInfo()
    var carbs: String = "",
    @ColumnInfo()
    var protein: String = "",
    @ColumnInfo()
    var fat: String = "",
    @ColumnInfo()
    var searched: Boolean = false




)