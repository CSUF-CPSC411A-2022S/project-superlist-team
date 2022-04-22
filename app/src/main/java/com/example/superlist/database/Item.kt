package com.example.superlist.database;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "item_table")
data class Item (

    // Defines the table's primary key. Primary keys are unique values that can be autogenerated.
    // They are used to differentiate one entity from another.
    @PrimaryKey(autoGenerate = true)
    var itemId: Long = 0L,

    // Non-rprimary key column. Data type is specified in the property.
    @ColumnInfo()
    var name: String = "",

    // Non-rprimary key column. Data type is specified in the property.
    @ColumnInfo()
    var price: String = ""
)