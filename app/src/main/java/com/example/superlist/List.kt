package com.example.superlist

class List<T> {
    fun <T> listOf() {
        val list = listOf<String>()
        println(list)
    }
}

