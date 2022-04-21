package com.example.superlist

import junit.framework.TestCase
import org.junit.Test

class FoodTest : TestCase() {

    @Test
    fun testGetFoodPrice() {
        val item = Food();
        assertEquals(25,
            item.getFoodPrice("apple"))

    }
}