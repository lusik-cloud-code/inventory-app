package com.grace.inventory.ui.screens.products


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.grace.inventory.data.Product
import com.grace.inventory.models.ProductViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID


@Composable
fun SalesEntryScreen(viewModel: ProductViewModel, navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    val allProducts = viewModel.productList
    val filteredProducts = allProducts.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Sales Entry",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Search Field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Product") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Product List
        LazyColumn {
            items(filteredProducts) { product ->
                SalesProductCard(product = product, viewModel = viewModel)
                //added a few things....
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Receipt",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(16.dp))
                ReceiptPreviewCard(saleItems = viewModel.productList)

            }
        }
    }
}

@Composable
fun SalesProductCard(product: Product, viewModel: ProductViewModel) {
    var sellingPrice by remember { mutableStateOf(product.price.toString()) }
    var quantitySold by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Product: ${product.name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Tag: ${product.tag}")
            Text(text = "Quantity Available: ${product.quantity}")

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = sellingPrice,
                onValueChange = { sellingPrice = it },
                label = { Text("Selling Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = quantitySold,
                onValueChange = { quantitySold = it },
                label = { Text("Quantity to Sell") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val price = sellingPrice.toDoubleOrNull() ?: 0.0
                    val quantity = quantitySold.toIntOrNull() ?: 0
                    if (quantity > 0 && quantity <= product.quantity) {
                        viewModel.recordSale(product, price, quantity)
                        sellingPrice = product.price.toString()
                        quantitySold = ""
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sell Product")
            }
        }
    }
}
@Composable
fun ReceiptPreviewCard(saleItems: List<Product>) {
    if (saleItems.isEmpty()) return

    val totalAmount = saleItems.sumOf { it.quantity * it.sellingPrice }
    val currentDate = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date())
    val orderId = UUID.randomUUID().toString().take(8)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("🧾 Receipt", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimaryContainer)

            Spacer(Modifier.height(8.dp))
            Text("Order No: $orderId")
            Text("Date: $currentDate")

            Spacer(Modifier.height(12.dp))

            saleItems.forEach { item ->
                Text("• ${item.productName} x${item.quantity} @ ${item.sellingPrice} = Ksh ${item.quantity * item.sellingPrice}")
            }

            Spacer(Modifier.height(12.dp))
            Text("Total: Ksh $totalAmount", fontWeight = FontWeight.Bold)
        }
    }
}
