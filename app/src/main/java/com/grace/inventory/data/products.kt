package com.grace.inventory.data

data class Product(
    var id: String = "",
    val name: String = "",
    val quantity: Int = 0,
    val price: Double = 0.0,
    var tag: String = "",

    val saleId: String = "",
    val productId: String = "",
    val productName: String = "",
    val sellingPrice: Double = 0.0,
    val quantitySold: Int = 0,
    val timestamp: Long = System.currentTimeMillis(),
    val totalAmount: Double = 0.0,

    val transactionId: Int=0,
    val date: Long=0L,

//WE BEGIN HERE
    val expenseId: Int=0,

    val totalPrice: Double=0.0,

)














data class Expense(
    val expenseId: Int=0,
    val productName: String="",
    val totalPrice: Double=0.0,
    val date: Long=0L,
)

data class SaleTransaction(
    val transactionId: String = "",
    val totalAmount: Double = 0.0,
    val date: Long = 0L,
//    val productName: String,
//    val quantity: Int,
)



