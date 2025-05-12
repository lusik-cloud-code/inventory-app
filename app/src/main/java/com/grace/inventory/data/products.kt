package com.grace.inventory.data

data class Product(
    var id: String = "",
    val name: String = "",
    val quantity: Int = 0,
    val price: Double = 0.0,
    var tag: String = ""
)


