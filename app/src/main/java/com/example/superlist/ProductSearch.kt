package com.example.superlist

data class ProductSearch(
    val type: String,
    val products: Array<Product>,
    val offset: Double,
    val number: Double,
    val totalProducts: Double,
    val processingTimeMs: Double,
    val expires: Double

)


// Represents a Place root object that contains an array of Feature objects
data class Product (
    val id: Double,
    val title: String,
    val image: String,
    val imageType: String
)