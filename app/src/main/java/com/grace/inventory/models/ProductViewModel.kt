package com.grace.inventory.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.grace.inventory.data.Expense
import com.grace.inventory.data.Product
import com.grace.inventory.data.SaleTransaction


class ProductViewModel : ViewModel() {
    var receiptItems = mutableStateListOf<Product>()
        private set

    private val dbRef = FirebaseDatabase.getInstance().getReference("products")
    var productList by mutableStateOf(listOf<Product>())
        private set

    init {
        fetchProducts()
        fetchSales()
        fetchExpenses()
    }

    fun fetchProducts() {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val products = mutableListOf<Product>()
                snapshot.children.forEach {
                    val product = it.getValue(Product::class.java)
                    product?.let { products.add(it) }
                }
                productList = products
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun addProduct(product: Product) {
        val id = dbRef.push().key ?: return
        product.id = id
        dbRef.child(id).setValue(product)
    }

    fun deleteProduct(productId: String) {
        dbRef.child(productId).removeValue()
    }
    fun updateProduct(productId: String, updatedProduct: Product) {
        dbRef.child(productId).setValue(updatedProduct)
    }


    //    need to delete this
    fun recordSale(product: Product, sellingPrice: Double, quantitySold: Int) {
        val saleId = dbRef.push().key ?: return

        val sale = Product(
            saleId = saleId,
            productId = product.id,
            productName = product.name,
            tag = product.tag,
            sellingPrice = sellingPrice,
            quantitySold = quantitySold,
            quantity = product.quantity,
        )

        // Clear previous receipt and add new one
        receiptItems.clear()
        receiptItems.add(sale)

        // Save to /sales
        dbRef.child("sales").child(saleId).setValue(sale)
            .addOnSuccessListener {
                val newQuantity = product.quantity - quantitySold
                dbRef.child("products").child(product.id).child("quantity").setValue(newQuantity)
            }
    }


    private fun fetchSales() {
        dbRef.child("sales").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val sales = mutableListOf<SaleTransaction>()
                snapshot.children.forEach {
                    val txn = it.getValue(SaleTransaction::class.java)
                    txn?.let { sales.add(it) }
                }
                saleTransactions = sales
            }

            override fun onCancelled(error: DatabaseError) {}
        })


    }

    var saleTransactions: List<SaleTransaction> by mutableStateOf(listOf())
        private set

    var expenseList: List<Expense> by mutableStateOf(listOf())
        private set

    // Example: Call this to load dummy or real data into the lists
    fun loadSampleData() {
        // TODO: Replace with actual Firebase fetch logic
        saleTransactions = listOf(
            SaleTransaction("TX001", 5000, System.currentTimeMillis())
        )

        val EX001 = 0
        expenseList = listOf(
            Expense(EX001, "Sugar", 2000, System.currentTimeMillis())
        )
    }
    private fun fetchExpenses() {
        FirebaseDatabase.getInstance().getReference("expenses")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = mutableListOf<Expense>()
                    snapshot.children.forEach {
                        val item = it.getValue(Expense::class.java)
                        item?.let { list.add(it) }
                    }
                    expenseList = list
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }

}




    // reduce quantity




        // total amount logic (optional)
//COMMENTED TO THIS POINT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//
////    private val _receiptItems = mutableStateListOf<SaleItem>()
//    val receiptItems: List<SaleItem> = _receiptItems
//
//    fun recordSale(product: Product, sellingPrice: Double, quantity: Int) {
//        // reduce quantity
//        product.quantity -= quantity
//
//        val saleItem = SaleItem(
//            productName = product.name,
//            quantity = quantity,
//            sellingPrice = sellingPrice
//        )
//
//        _receiptItems.add(saleItem)

        // total amount logic (optional)
//
//
//import androidx.compose.runtime.*
//import androidx.lifecycle.ViewModel
//import com.google.firebase.database.*
//import com.grace.inventory.data.Product
//import com.grace.inventory.data.SaleTransaction
//
//class InventoryViewModel : ViewModel() {
//    private val dbRef = FirebaseDatabase.getInstance().reference
//
//    var productList by mutableStateOf(listOf<Product>())
//        private set
//
//    var receiptItems = mutableStateListOf<Product>()
//        private set
//
//    var saleTransactions by mutableStateOf(listOf<SaleTransaction>())
//        private set
//
//    init {
//        fetchProducts()
//        fetchSales()
//    }
//
//    // 🔄 Fetch Products
//    private fun fetchProducts() {
//        dbRef.child("products").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val products = mutableListOf<Product>()
//                snapshot.children.forEach {
//                    val product = it.getValue(Product::class.java)
//                    product?.let { products.add(it) }
//                }
//                productList = products
//            }
//
//            override fun onCancelled(error: DatabaseError) {}
//        })
//    }
//
//    // ➕ Add Product
//    fun addProduct(product: Product) {
//        val id = dbRef.child("products").push().key ?: return
//        product.id = id
//        dbRef.child("products").child(id).setValue(product)
//    }
//
//    // ❌ Delete Product
//    fun deleteProduct(productId: String) {
//        dbRef.child("products").child(productId).removeValue()
//    }
//
//    // ✏️ Update Product
//    fun updateProduct(productId: String, updatedProduct: Product) {
//        dbRef.child("products").child(productId).setValue(updatedProduct)
//    }
//
//    // 🧾 Record a Sale + Update Product Quantity
//    fun recordSale(product: Product, sellingPrice: Double, quantitySold: Int) {
//        val saleId = dbRef.child("sales").push().key ?: return
//        val timestamp = System.currentTimeMillis()
//        val totalPrice = sellingPrice * quantitySold
//
//        // Add to receipt display
//        val soldItem = product.copy(
//            sellingPrice = sellingPrice,
//            quantitySold = quantitySold
//        )
//        receiptItems.add(soldItem)
//
//        // Create transaction
//        val transaction = SaleTransaction(
//            transactionId = saleId,
//            date = timestamp,
//            totalAmount = totalPrice,
//            productName = product.name,
//            quantity = quantitySold
//        )
//
//        // Save to Firebase
//        dbRef.child("sales").child(saleId).setValue(transaction).addOnSuccessListener {
//            // Update product quantity
//            val newQty = product.quantity - quantitySold
//            dbRef.child("products").child(product.id).child("quantity").setValue(newQty)
//        }
//    }

    // 🔁 Fetch Sales Transactions




