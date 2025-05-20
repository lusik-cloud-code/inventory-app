package com.grace.inventory.data

data class Expense(
    val expenseId: Int = 0,
    val productName: String = "",
    val totalPrice: Int = 0,
    val date: Long = 0L,
    val timestamp: Long = 0L
)
