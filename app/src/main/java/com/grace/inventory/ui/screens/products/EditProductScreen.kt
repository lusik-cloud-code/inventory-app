package com.grace.inventory.ui.screens.products

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.grace.inventory.models.ProductViewModel

@Composable
fun EditProductScreen(productId: String, viewModel: ProductViewModel,navController: NavHostController) {
    val product = viewModel.productList.find { it.id == productId }

    if (product != null) {
        // Show editable fields for this product
    } else {
        Text("Product not found")
    }
}
