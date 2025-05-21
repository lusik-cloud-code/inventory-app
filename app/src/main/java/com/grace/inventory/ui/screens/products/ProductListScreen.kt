package com.grace.inventory.ui.screens.products

//
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Build
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Edit
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.grace.inventory.models.ProductViewModel
//import com.grace.inventory.navigation.ROUTE_ADD_ITEM
//
//@Composable
//fun ProductListScreen(viewModel: ProductViewModel, navController: NavHostController) {
//    val productList = viewModel.productList
//    var searchQuery by remember { mutableStateOf("") }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        OutlinedTextField(
//            value = searchQuery,
//            onValueChange = { searchQuery = it },
//            label = { Text("Search Products") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(Modifier.height(16.dp))
//
//        LazyColumn {
//            val filtered = productList.filter { it.name.contains(searchQuery, ignoreCase = true) }
//            items(filtered) { product ->
//                Card(modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp),
//                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
//
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Column {
//                            Text("Name: ${product.name}")
//                            Text("Qty: ${product.quantity}")
//                            Text("Price: KES ${product.price}")
//                        }
//
//                        Row {
//                            IconButton(onClick = {
//                                // Example edit functionality; you'd route to a separate EditScreen
//                                navController.navigate("editProduct/${product.id}")
//                            }) {
//                                Icon(Icons.Default.Edit, contentDescription = "Edit")
//                            }
//
//                            IconButton(onClick = {
//                                viewModel.deleteProduct(product.id)
//                            }) {
//                                Icon(Icons.Default.Delete, contentDescription = "Delete")
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = {
//            navController.navigate(ROUTE_ADD_ITEM)
//        }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
//            Text("Add New Product")
//        }
//    }
//}
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.grace.inventory.models.ProductViewModel
import com.grace.inventory.navigation.ROUTE_ADD_ITEM
import com.grace.inventory.navigation.ROUTE_EDIT

@Composable
fun ProductListScreen(viewModel: ProductViewModel, navController: NavHostController) {
    val productList = viewModel.productList
    val productId = "someProductId" // This is the ID you want to pass
    var searchQuery by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = androidx.compose.ui.graphics.Brush.linearGradient(
                    colors = listOf(Color.Black, Color(0xFF0F0F3E), Color(0xFF001133))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Products", color = Color.Cyan) },
                modifier = Modifier
                    .fillMaxWidth()
                    .neonGlow(Color.Cyan),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Cyan,
                    unfocusedBorderColor = Color.Cyan.copy(alpha = 0.5f),
                    cursorColor = Color.Cyan
                ),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                val filtered = productList.filter {
                    it.productName.contains(searchQuery, ignoreCase = true)
                }
                items(filtered) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                            .neonGlow(Color.Cyan),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF111133)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("Name: ${product.productName}", color = Color.White)
                                Text("Qty: ${product.quantity}", color = Color.White)
                                Text("Price: KES ${product.sellingPrice}", color = Color.White)
                            }

                            Row {
                                IconButton(
                                    onClick = {
                                        navController.navigate("editproductscreen/${product.productId}")
                                    },
                                    modifier = Modifier.neonGlow(Color.Cyan)
                                ) {
                                    Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.Cyan)
                                }

                                IconButton(
                                    onClick = {
                                        viewModel.deleteProduct(product.productId)
                                    },
                                    modifier = Modifier.neonGlow(Color.Red)
                                ) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            NeonButton(
                text = "Add New Product",
                onClick = { navController.navigate(ROUTE_ADD_ITEM) },
                backgroundColor = Color.Magenta
            )
        }
    }
}

@Composable
fun NeonButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .neonGlow(Color.Magenta),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = text, color = Color.White, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
    }
}

/**
 * Simple neon glow effect:
 * Draws layered transparent rounded rectangles behind the component.
 */
fun Modifier.neonGlow(color: Color = Color.Cyan): Modifier = this.then(
    Modifier.drawBehind {
        val cornerRadius = 12.dp.toPx()
        // Outer faint glow
        drawRoundRect(
            color = color.copy(alpha = 0.15f),
            size = size,
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius, cornerRadius)
        )
        // Inner stronger glow
        drawRoundRect(
            color = color.copy(alpha = 0.3f),
            size = size,
            cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius * 0.7f, cornerRadius * 0.7f)
        )
    }
)
