package com.example.superlist

data class ItemPrice(
    val unit: String,
    val amount: Double,

)

data class ItemData (
    val name: String,
    val amount: Double,
    val indented: Boolean,
)