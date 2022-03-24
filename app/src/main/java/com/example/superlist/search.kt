package com.example.superlist

class search {

    fun getsearchImage(searchString: String): String? {
        when(searchString) {
            "apple" -> return "/apple.png"
            "orange" -> return "/orange.png"
            else -> return null

        }
    }
}