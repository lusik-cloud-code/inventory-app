package com.grace.inventory.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.grace.inventory.data.Product


class ProductViewModel : ViewModel() {
    private val dbRef = FirebaseDatabase.getInstance().getReference("products")
    var productList by mutableStateOf(listOf<Product>())
        private set

    init {
        fetchProducts()
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

    fun updateProduct(product: Product) {
        dbRef.child(product.id).setValue(product)
    }
}
