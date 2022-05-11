package com.example.superlist

data class Profile(
    val item_price: String,
    val item: Array<Item>,
)

data class Item (
    val profiles: Array<Profile>
)