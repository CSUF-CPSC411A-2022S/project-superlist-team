package com.example.superlist


data class Nutrition(
    val calories: String,
    val carbs: String,
    val fat: String,
    val protein: String,
    val bad: Array<FoodData>,
    val good: Array<FoodData>,
    val expires: Double,
    val isStale: Boolean
)

data class FoodData (
    val title: String,
    val amount: String,
    val indented: Boolean,
    val percentOfDailyNeeds: Double,
        )