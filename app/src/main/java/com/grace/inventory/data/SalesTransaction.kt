package com.grace.inventory.data


data class SaleTransaction(
    val transactionId: String = "",
    val productId: String = "",
    val productName: String = "",
    val sellingPrice: Double = 0.0,
    val quantitySold: Long = 0L,         // Changed from Int to Long
    val totalAmount: Long = 0L,          // Changed from Int to Long
    val timestamp: Long = 0L
)