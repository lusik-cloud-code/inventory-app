package com.grace.inventory.ui.screens.products



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.grace.inventory.data.Product
import com.grace.inventory.models.ProductViewModel

@Composable
fun ProductListScreen(viewModel: ProductViewModel, navController: NavHostController) {
    val productList = viewModel.productList
    var searchQuery by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search Products") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        LazyColumn {
            val filtered = productList.filter { it.name.contains(searchQuery, ignoreCase = true) }
            items(filtered) { product ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("Name: ${product.name}")
                            Text("Qty: ${product.quantity}")
                            Text("Price: KES ${product.price}")
                        }

                        Row {
                            IconButton(onClick = {
                                // Example edit functionality; you'd route to a separate EditScreen
                                navController.navigate("editProduct/${product.id}")
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = "Edit")
                            }

                            IconButton(onClick = {
                                viewModel.deleteProduct(product.id)
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Delete")
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("addProduct")
        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Add New Product")
        }
    }
}
