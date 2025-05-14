package com.grace.inventory.ui.screens.products



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.grace.inventory.data.Product
import com.grace.inventory.models.ProductViewModel
import com.grace.inventory.navigation.ROUTE_CURRENT_STOCK


@Composable
fun AddItemScreen(viewModel: ProductViewModel, navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Product Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Quantity") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(20.dp))

        Button(onClick = {{
            navController.navigate(ROUTE_CURRENT_STOCK)
        }
            val product = Product(
                name = name,
                quantity = quantity.toIntOrNull() ?: 0,
                price = price.toDoubleOrNull() ?: 0.0
            )
            viewModel.addProduct(product)
            navController.popBackStack()
        }) {
            Text("Add Product")
        }
    }
}
@Preview
@Composable
fun AddItemScreenPreview() {
    val viewModel: ProductViewModel = viewModel()
    AddItemScreen(viewModel = viewModel, navController = rememberNavController())
}
