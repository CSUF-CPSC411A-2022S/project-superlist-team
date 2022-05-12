package com.example.superlist


data class Nutrition(
    val calories: String = "",
    val carbs: String = "",
    val fat: String = "",
    val protein: String = "",
    val bad: Array<FoodData> = emptyArray(),
    val good: Array<FoodData> = emptyArray(),
    val expires: Double = 0.0,
    val isStale: Boolean = true
)

data class FoodData (
    val title: String = "",
    val amount: String = "",
    val indented: Boolean = true,
    val percentOfDailyNeeds: Double = 0.0,
        )