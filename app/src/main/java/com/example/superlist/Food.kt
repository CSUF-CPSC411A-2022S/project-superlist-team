package com.example.superlist

class Food {

    //Sample API call to get price of a food given a string.
    fun getFoodPrice(foodString: String): Int {
        when(foodString) {
            "apple" -> return 25
            "orange" -> return 30
            else -> return 0
        }
    }

}