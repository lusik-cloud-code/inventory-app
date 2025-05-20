package com.grace.inventory.data

data class SaleTransaction(
    val transactionId: String = "",
    val productId: String = "",
    val productName: String = "",
    val sellingPrice: Double = 0.0,
    val quantitySold: Int = 0,
    val totalAmount: Int = 0,
    val timestamp: Long = 0L
)
