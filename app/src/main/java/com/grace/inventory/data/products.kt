package com.grace.inventory.data

import java.time.temporal.TemporalAmount
//
//
//data class Product(
//    var productId: String = "",
//    var productName: String = "",
//
//    var totalPrice: Double = 0.0,
//    var totalAmount: Double = 0.0,
//    var date: String = "",
//    var timestamp: Long = 0L,
//    var expenseId: String = "",
//    var saleId: String = "",
//    var transactionId: String = "",
//    var quantitySold: Int = 0,
//    var tag: String = "",
//    val quantity: Int = 0,            // ✅ now it's a number
//    val sellingPrice: Double = 0.0
//)
//
//data class SaleItem(
//    val productId: String="",
//    val productName: String="",
//    val quantitySold: Int=0,
//    val totalAmount: Int=0,
//    val quantity: Int = 0,            // ✅ now it's a number
//    val sellingPrice: Double = 0.0
//)



data class Product(
    var productId: String = "",
    var productName: String = "",
    var totalPrice: Double = 0.0,
    var totalAmount: Double = 0.0,
    var date: String = "",
    var timestamp: Long = 0L,
    var expenseId: String = "",
    var saleId: String = "",
    var transactionId: String = "",
    var quantitySold: Int = 0,         // 🔁 changed from Int → Long
    var tag: String = "",
    val quantity: Long = 0,             // 🔁 changed from Int → Long
    val sellingPrice: Double = 0.0
)

data class SaleItem(
    val productId: String = "",
    val productName: String = "",
    val quantitySold: Int = 0,         // 🔁 changed from Int → Long
    val totalAmount: Int = 0,
    val quantity: Long = 0,             // 🔁 changed from Int → Long
    val sellingPrice: Double = 0.0
)
