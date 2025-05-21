//package com.grace.inventory.ui.screens.products
//
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.material3.Icon
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.OutlinedTextFieldDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.grace.inventory.models.ProductViewModel
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.saveable.rememberSaveable
//
//
//// Dummy Product data class for demonstration purposes
//
//
//// Dummy ProductViewModel for demonstration purposes
//// In a real app, this would typically be a ViewModel class integrated with a database or repository
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun EditProductScreen(
//    productId: String,
//    viewModel: ProductViewModel,
//    navController: NavHostController
//) {
//    // Find the product to edit from the ViewModel's list
//    val product = viewModel.productList.find { it.productId == productId }
//
//    // State variables for the editable fields, initialized with product data or empty strings
//    // These will be updated by LaunchedEffect when the product is found
//    var productName by rememberSaveable { mutableStateOf("") }
//    var quantity by rememberSaveable { mutableStateOf("") }
//    var price by rememberSaveable { mutableStateOf("") } // Renamed from sellingprice to price for consistency
//
//    // Use LaunchedEffect to update the state variables if the 'product' object changes.
//    // This is crucial for initializing the text fields with the existing product data.
//    LaunchedEffect(product) {
//        product?.let {
//            productName = it.productName
//            quantity = it.quantity.toString()
//            price = it.sellingPrice.toString()
//        }
//    }
//
//    val deepBlue = Color(0xFF0D1B2A)
//    val vibrantPurple = Color(0xFF7F00FF)
//    val softPurple = Color(0xFF9C27B0)
//    val white = Color.White
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text(if (product != null) "Edit Product" else "Product Not Found", color = white) },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = white)
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = deepBlue)
//            )
//        },
//        containerColor = deepBlue // Set Scaffold background color
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues) // Apply padding from Scaffold
//                .padding(horizontal = 16.dp, vertical = 8.dp), // Add additional padding for content
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top // Changed to Top for better layout with TopAppBar
//        ) {
//            if (product != null) {
//                Spacer(modifier = Modifier.height(20.dp)) // Increased spacing
//
//                // Product Name TextField
//                OutlinedTextField(
//                    value = productName,
//                    onValueChange = { productName = it },
//                    label = { Text("Product Name", color = softPurple) },
//                    textStyle = TextStyle(color = white),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//                    leadingIcon = { Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = softPurple) },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 6.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = vibrantPurple,
//                        unfocusedBorderColor = softPurple,
//                        cursorColor = vibrantPurple,
//                        focusedLabelColor = vibrantPurple,
//                        unfocusedLabelColor = softPurple,
//                        focusedLeadingIconColor = vibrantPurple,
//                        unfocusedLeadingIconColor = softPurple,
//                        focusedContainerColor = Color.Transparent, // Make background transparent
//                        unfocusedContainerColor = Color.Transparent
//                    )
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Quantity TextField
//                OutlinedTextField(
//                    value = quantity,
//                    onValueChange = { newValue ->
//                        // Allow only digits for quantity
//                        if (newValue.all { it.isDigit() }) {
//                            quantity = newValue
//                        }
//                    },
//                    label = { Text("Quantity", color = softPurple) },
//                    textStyle = TextStyle(color = white),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                    leadingIcon = { Icon(Icons.Default.Add, contentDescription = null, tint = softPurple) }, // Changed icon
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 6.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = vibrantPurple,
//                        unfocusedBorderColor = softPurple,
//                        cursorColor = vibrantPurple,
//                        focusedLabelColor = vibrantPurple,
//                        unfocusedLabelColor = softPurple,
//                        focusedLeadingIconColor = vibrantPurple,
//                        unfocusedLeadingIconColor = softPurple,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent
//                    )
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Price TextField (renamed from sellingprice)
//                OutlinedTextField(
//                    value = price,
//                    onValueChange = { newValue ->
//                        // Allow digits and a single decimal point for price
//                        if (newValue.matches(Regex("^\\d*\\.?\\d*\$"))) {
//                            price = newValue
//                        }
//                    },
//                    label = { Text("Price", color = softPurple) },
//                    textStyle = TextStyle(color = white),
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                    // PasswordVisualTransformation is not suitable for price, removed it
//                    leadingIcon = { Icon(Icons.Default.Star, contentDescription = null, tint = softPurple) }, // Changed icon
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 6.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = OutlinedTextFieldDefaults.colors(
//                        focusedBorderColor = vibrantPurple,
//                        unfocusedBorderColor = softPurple,
//                        cursorColor = vibrantPurple,
//                        focusedLabelColor = vibrantPurple,
//                        unfocusedLabelColor = softPurple,
//                        focusedLeadingIconColor = vibrantPurple,
//                        unfocusedLeadingIconColor = softPurple,
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent
//                    )
//                )
//
//                Spacer(modifier = Modifier.height(32.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    // Save Changes Button
//                    Button(
//                        onClick = {
//                            // Basic validation for input fields
//                            val parsedQuantity = quantity.toIntOrNull()
//                            val parsedPrice = price.toDoubleOrNull()
//
//                            if (productName.isNotBlank() && parsedQuantity != null && parsedPrice != null) {
//                                // Create an updated Product object
//                                val updateProduct = product.copy(
//                                    productName = productName,
//                                    quantity = quantity.toLongOrNull() ?: 0,
//                                    sellingPrice = price.toDoubleOrNull() ?: 0.0
//
//                                )
//                                // Call ViewModel to update the product
//                                viewModel.updateProduct(updateProduct)
//                                navController.popBackStack() // Navigate back after successful save
//                            } else {
//                                // In a real app, you'd show a Snackbar or Toast here
//                                println("Validation failed: Please ensure all fields are valid.")
//                            }
//                        },
//                        modifier = Modifier.weight(1f),
//                        enabled = productName.isNotBlank() && quantity.toIntOrNull() != null && price.toDoubleOrNull() != null,
//                        colors = ButtonDefaults.buttonColors(containerColor = vibrantPurple)
//                    ) {
//                        Text("Save Changes", color = white)
//                    }
//                    Spacer(modifier = Modifier.width(8.dp))
//
//                    // Delete Product Button
//                    Button(
//                        onClick = {
//                            // Call ViewModel to delete the product
//                            viewModel.deleteProduct(product.productId)
//                            navController.popBackStack() // Navigate back after deleting
//                        },
//                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
//                        modifier = Modifier.weight(1f)
//                    ) {
//                        Text("Delete Product", color = white)
//                    }
//                }
//            } else {
//                // Displayed if the product is not found
//                Spacer(modifier = Modifier.height(32.dp))
//                Text("Product not found", style = MaterialTheme.typography.headlineSmall, color = white)
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(onClick = { navController.popBackStack() }, colors = ButtonDefaults.buttonColors(containerColor = vibrantPurple)) {
//                    Text("Go Back", color = white)
//                }
//            }
//        }
//    }
//}

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add

import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.runtime.snapshots.SnapshotStateList // Required for mutableStateListOf
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Star

import com.grace.inventory.models.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(
    productId: String,
    viewModel: ProductViewModel,
    navController: NavHostController
) {
    val productList by remember { derivedStateOf { viewModel.productList } }

    val product = productList.find { it.productId == productId }

    var productName by rememberSaveable { mutableStateOf("") }
    var quantity by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(product?.productId) {
        product?.let {
            productName = it.productName
            quantity = it.quantity.toString()
            price = it.sellingPrice.toString()
        }
    }

    val deepBlue = Color(0xFF0D1B2A)
    val vibrantPurple = Color(0xFF7F00FF)
    val softPurple = Color(0xFF9C27B0)
    val white = Color.White

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (product != null) "Edit Product" else "Product Not Found", color = white) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = white)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = deepBlue)
            )
        },
        containerColor = deepBlue
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            if (product != null) {
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    label = { Text("Product Name", color = softPurple) },
                    textStyle = TextStyle(color = white),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    leadingIcon = { Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = softPurple) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = vibrantPurple,
                        unfocusedBorderColor = softPurple,
                        cursorColor = vibrantPurple,
                        focusedLabelColor = vibrantPurple,
                        unfocusedLabelColor = softPurple,
                        focusedLeadingIconColor = vibrantPurple,
                        unfocusedLeadingIconColor = softPurple,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = quantity,
                    onValueChange = { newValue ->
                        if (newValue.all { it.isDigit() }) {
                            quantity = newValue
                        }
                    },
                    label = { Text("Quantity", color = softPurple) },
                    textStyle = TextStyle(color = white),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    leadingIcon = { Icon(Icons.Default.Add, contentDescription = null, tint = softPurple) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = vibrantPurple,
                        unfocusedBorderColor = softPurple,
                        cursorColor = vibrantPurple,
                        focusedLabelColor = vibrantPurple,
                        unfocusedLabelColor = softPurple,
                        focusedLeadingIconColor = vibrantPurple,
                        unfocusedLeadingIconColor = softPurple,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = price,
                    onValueChange = { newValue ->
                        if (newValue.matches(Regex("^\\d*\\.?\\d*\$"))) {
                            price = newValue
                        }
                    },
                    label = { Text("Price", color = softPurple) },
                    textStyle = TextStyle(color = white),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    leadingIcon = { Icon(Icons.Default.Star, contentDescription = null, tint = softPurple) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = vibrantPurple,
                        unfocusedBorderColor = softPurple,
                        cursorColor = vibrantPurple,
                        focusedLabelColor = vibrantPurple,
                        unfocusedLabelColor = softPurple,
                        focusedLeadingIconColor = vibrantPurple,
                        unfocusedLeadingIconColor = softPurple,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            val parsedQuantity = quantity.toLongOrNull()
                            val parsedPrice = price.toDoubleOrNull()

                            if (productName.isNotBlank() && parsedQuantity != null && parsedPrice != null) {
                                val updatedProduct = product.copy(
                                    productName = productName,
                                    quantity = parsedQuantity,
                                    sellingPrice = parsedPrice
                                )
                                viewModel.updateProduct(updatedProduct)
                                navController.popBackStack()
                            } else {
                                println("Validation failed: Fill all fields properly")
                            }
                        },
                        modifier = Modifier.weight(1f),
                        enabled = productName.isNotBlank() && quantity.toLongOrNull() != null && price.toDoubleOrNull() != null,
                        colors = ButtonDefaults.buttonColors(containerColor = vibrantPurple)
                    ) {
                        Text("Save Changes", color = white)
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            viewModel.deleteProduct(product.productId)
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Delete Product", color = white)
                    }
                }
            } else {
                Spacer(modifier = Modifier.height(32.dp))
                Text("Product not found", style = MaterialTheme.typography.headlineSmall, color = white)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = vibrantPurple)
                ) {
                    Text("Go Back", color = white)
                }
            }
        }
    }
}
