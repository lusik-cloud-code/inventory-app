package com.grace.inventory.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.grace.inventory.data.Expense
import com.grace.inventory.data.Product
import com.grace.inventory.data.SaleItem
import com.grace.inventory.data.SaleTransaction

class ProductViewModel : ViewModel() {
    // List for displaying receipt items (products that have been sold)
    var receiptItems = mutableStateListOf<SaleItem>()
        private set


    // Firebase Database reference for products
    private val dbRef = FirebaseDatabase.getInstance().getReference("products")

    // List of products fetched from Firebase
    var productList by mutableStateOf(listOf<Product>())
        private set

    // List of sales transactions fetched from Firebase
    var saleTransactions: List<SaleTransaction> by mutableStateOf(listOf())
        private set

    // List of expenses fetched from Firebase
    var expenseList: List<Expense> by mutableStateOf(listOf())
        private set

    init {
        fetchProducts()
        fetchExpenses()
    }

    fun addProduct(product: Product) {
        val id = dbRef.push().key ?: return
        product.productId = id
        dbRef.child(id).setValue(product)
    }

    // Remove a product from Firebase
    fun deleteProduct(productId: String) {
        dbRef.child(productId).removeValue()
    }

    // Update an existing product in Firebase
//    fun updateProduct(updatedProduct: Product) {
//        dbRef.child(updatedProduct.productId).setValue(updatedProduct)
//    }
//
    fun updateProduct(updatedProduct: Product) {
        dbRef.child(updatedProduct.productId).setValue(updatedProduct)
    }


//    fun recordSale(product: Product, sellingPrice: Double, quantitySold: Long) {
//        val saleId = FirebaseDatabase.getInstance().getReference("sales").push().key ?: return
//
//        val totalAmount = (sellingPrice * quantitySold).toLong()  // Convert to Long instead of Int
//
//        val sale = SaleTransaction(
//            transactionId = saleId,
//            productId = product.productId,
//            productName = product.productName,
//            sellingPrice = sellingPrice,
//            quantitySold = quantitySold,      // No conversion needed
//            totalAmount = totalAmount,        // No conversion needed
//            timestamp = System.currentTimeMillis()
//        )
//
//        // Rest of the code remains the same...
//
//        // Update this part too
//        receiptItems.clear()
//        receiptItems.add(
//            SaleItem(
//                productId = product.productId,
//                productName = product.productName,
//                quantitySold = quantitySold,  // No conversion needed
//                sellingPrice = sellingPrice,
//                totalAmount = totalAmount     // No conversion needed
//            )
//        )
//    }
fun recordSale(product: Product, sellingPrice: Double, quantitySold: Long) {
    val saleId = FirebaseDatabase.getInstance().getReference("sales").push().key ?: return
    val totalAmount = (sellingPrice * quantitySold).toLong()

    val sale = SaleTransaction(
        transactionId = saleId,
        productId = product.productId,
        productName = product.productName,
        sellingPrice = sellingPrice,
        quantitySold = quantitySold,
        totalAmount = totalAmount,
        timestamp = System.currentTimeMillis()
    )

    // Save sale to Firebase...
    FirebaseDatabase.getInstance().getReference("sales").child(saleId).setValue(sale)

    // Check if product already in receipt and update
    val existingIndex = receiptItems.indexOfFirst { it.productId == product.productId && it.sellingPrice == sellingPrice }
    if (existingIndex >= 0) {
        val existing = receiptItems[existingIndex]
        receiptItems[existingIndex] = existing.copy(
            quantity = existing.quantity + quantitySold,
            totalAmount = (existing.totalAmount + totalAmount)
        )
    } else {
        receiptItems.add(
            SaleItem(
                productId = product.productId,
                productName = product.productName,
                quantity = quantitySold,
                sellingPrice = sellingPrice,
                totalAmount = totalAmount
            )
        )
    }
}


    fun fetchProducts() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val products = mutableListOf<Product>()

                snapshot.children.forEach { childSnapshot ->
                    try {
                        // Manual conversion to avoid the error
                        val map = childSnapshot.value as? Map<*, *> ?: return@forEach

                        val product = Product(
                            productId = map["productId"]?.toString() ?: childSnapshot.key ?: "",
                            productName = map["productName"]?.toString() ?: "",
                            totalPrice = (map["totalPrice"] as? Number)?.toDouble() ?: 0.0,
                            totalAmount = (map["totalAmount"] as? Number)?.toDouble() ?: 0.0,
                            date = map["date"]?.toString() ?: "",
                            timestamp = (map["timestamp"] as? Number)?.toLong() ?: 0L,
                            expenseId = map["expenseId"]?.toString() ?: "",
                            saleId = map["saleId"]?.toString() ?: "",
                            transactionId = map["transactionId"]?.toString() ?: "",
                            quantitySold = (map["quantitySold"] as? Number)?.toLong() ?: 0L,
                            tag = map["tag"]?.toString() ?: "",
                            quantity = (map["quantity"] as? Number)?.toLong() ?: 0L,
                            sellingPrice = (map["sellingPrice"] as? Number)?.toDouble() ?: 0.0
                        )

                        products.add(product)
                    } catch (e: Exception) {
                        // Log error and continue with next product
                        println("Error parsing product: ${e.message}")
                    }
                }

                productList = products
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }
    // Fetch expenses from Firebase
    private fun fetchExpenses() {
        FirebaseDatabase.getInstance().getReference("expenses")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val expenses = mutableListOf<Expense>()
                    snapshot.children.forEach {
                        val expense = it.getValue(Expense::class.java)
                        expense?.let { expenses.add(it) }
                    }
                    expenseList = expenses
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error if needed
                }
            })
    }

    // Filter sales transactions by a given date range.
    fun filterSalesByDate(start: Long, end: Long): List<SaleTransaction> {
        return saleTransactions.filter { it.timestamp in start..end }
    }

    // Filter expenses by a given date range.
    fun filterExpensesByDate(start: Long, end: Long): List<Expense> {
        return expenseList.filter { it.timestamp in start..end }
    }

    // Calculate profit by summing total sales and subtracting total expenses in a date range.
    fun calculateProfit(start: Long, end: Long): Long {  // Change return type to Long
        val totalSales = filterSalesByDate(start, end).sumOf { it.totalAmount }
        val totalExpenses = filterExpensesByDate(start, end).sumOf { it.totalPrice.toLong() }
        return totalSales - totalExpenses
    }

    // Load sample data (for testing or debugging purposes)
    fun loadSampleData() {
        saleTransactions = listOf(
            SaleTransaction(
                transactionId = "TX001",
                productId = "P001",
                productName = "Sample",
                sellingPrice = 1000.0,
                quantitySold = 5,
                totalAmount = 5000,
                timestamp = System.currentTimeMillis()
            )
        )

        // Example expense with id "0" (adjust parameters as needed)
        expenseList = listOf(
            Expense(0, "Sugar", 2000, System.currentTimeMillis())
        )
    }
}
