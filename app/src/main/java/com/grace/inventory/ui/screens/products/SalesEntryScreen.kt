package com.grace.inventory.ui.screens.products


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.grace.inventory.data.Product
import com.grace.inventory.data.SaleItem
import com.grace.inventory.models.ProductViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

/**
 * Sales Entry Screen
 *
 * This screen allows users to search for products,
 * select quantities, and add items to a receipt.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesEntryScreen(viewModel: ProductViewModel, navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    val allProducts = viewModel.productList
    val filteredProducts = allProducts.filter {
        it.productName.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Sales Entry",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Search Field with enhanced styling
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Product") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    cursorColor = MaterialTheme.colorScheme.primary
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Section Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "Products",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Available Products",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Product List
            LazyColumn {
                items(filteredProducts) { product ->
                    SalesProductCard(product = product, viewModel = viewModel)
                    Spacer(modifier = Modifier.height(8.dp))
                    Spacer(modifier = Modifier.height(8.dp))
                    ReceiptPreviewCard(saleItems = viewModel.receiptItems)

            // Receipt Section
            if (viewModel.receiptItems.isNotEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Receipt",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Receipt",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }


            }
                }
            }
        }
    }
}

/**
 * Card component for displaying product information and selling options
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesProductCard(product: Product, viewModel: ProductViewModel) {
    var sellingPrice by remember { mutableStateOf(product.sellingPrice.toString()) }
    var quantitySold by remember { mutableStateOf("") }

    // Separate error states for each field for better UX
    var priceError by remember { mutableStateOf<String?>(null) }
    var quantityError by remember { mutableStateOf<String?>(null) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Product Info Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = product.productName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Tag: ${product.tag}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "In Stock: ${product.quantity}",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.outlineVariant, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Selling Price Input
            OutlinedTextField(
                value = sellingPrice,
                onValueChange = {
                    sellingPrice = it
                    priceError = null
                },
                label = { Text("Selling Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = priceError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                ),
                leadingIcon = {
                    Text(
                        "Ksh",
                        modifier = Modifier.padding(start = 8.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            )
            if (priceError != null) {
                Text(
                    text = priceError ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Quantity to Sell Input
            OutlinedTextField(
                value = quantitySold,
                onValueChange = {
                    quantitySold = it
                    quantityError = null
                },
                label = { Text("Quantity to Sell") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = quantityError != null,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                )
            )
            if (quantityError != null) {
                Text(
                    text = quantityError ?: "",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sell Button - disabled when invalid inputs
            val price = sellingPrice.toDoubleOrNull()
            val quantity = quantitySold.toIntOrNull()
            val isButtonEnabled = price != null && price > 0 && quantity != null && quantity > 0 && quantity <= product.quantity

            Button(
                onClick = {
                    when {
                        price == null || price <= 0 -> priceError = "Please enter a valid price"
                        quantity == null || quantity <= 0 -> quantityError = "Please enter a valid quantity"
                        quantity > product.quantity -> quantityError = "Insufficient stock available"
                        else -> {
                            viewModel.recordSale(product, price, quantity.toLong())
                            // Reset fields
                            sellingPrice = product.sellingPrice.toString()
                            quantitySold = ""
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isButtonEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isButtonEnabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
                    contentColor = if (isButtonEnabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    "Sell Product",
                    modifier = Modifier.padding(vertical = 4.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


/**
 * Displays the receipt with all items and totals
 */
@Composable
fun ReceiptPreviewCard(saleItems: List<SaleItem>) {
    if (saleItems.isEmpty()) return

    val totalAmount = saleItems.sumOf { it.quantity * it.sellingPrice }
    val currentDate = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date())
    val orderId = UUID.randomUUID().toString().take(8)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Receipt Header
            Text(
                "🧾 Sale Receipt",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(8.dp))

            // Receipt Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Order #$orderId",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    currentDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f))
            Spacer(Modifier.height(16.dp))

            // Items List
            Column {
                // Header row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Item",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        "Qty",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        "Price",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        "Total",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Item rows
                saleItems.forEach { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "${item.productName}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.weight(2f)
                        )
                        Text(
                            "${item.quantity}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            "Ksh ${item.sellingPrice}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            "Ksh ${item.quantity * item.sellingPrice}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            Divider(color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f))
            Spacer(Modifier.height(16.dp))

            // Total
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Total Amount",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    "Ksh $totalAmount",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Thank You Message
            Text(
                "Thank you for your business!",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
