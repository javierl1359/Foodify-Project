package com.example.fodify

data class FoodData(
    val title: String,
    val logo: Int,
    val desc: String,
    var isExpandable: Boolean = false
)