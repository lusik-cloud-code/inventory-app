package com.grace.inventory.ui.screens.products


//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.grace.inventory.data.Product
//import com.grace.inventory.models.ProductViewModel
//import com.grace.inventory.navigation.ROUTE_CURRENT_STOCK
//
//
//@Composable
//fun AddItemScreen(viewModel: ProductViewModel, navController: NavHostController) {
//    var name by remember { mutableStateOf("") }
//    var quantity by remember { mutableStateOf("") }
//    var price by remember { mutableStateOf("") }
//
//    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {
//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text("Product Name") },
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(Modifier.height(10.dp))
//
//        OutlinedTextField(
//            value = quantity,
//            onValueChange = { quantity = it },
//            label = { Text("Quantity") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(Modifier.height(10.dp))
//
//        OutlinedTextField(
//            value = price,
//            onValueChange = { price = it },
//            label = { Text("Price") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            modifier = Modifier.fillMaxWidth()
//        )
//        Spacer(Modifier.height(20.dp))
//
//        Button(onClick = {{
//            navController.navigate(ROUTE_CURRENT_STOCK)
//        }
//            val product = Product(
//                name = name,
//                quantity = quantity.toIntOrNull() ?: 0,
//                price = price.toDoubleOrNull() ?: 0.0
//            )
//            viewModel.addProduct(product)
//            navController.popBackStack()
//        }) {
//            Text("Add Product")
//        }
//    }
//}
//@Preview
//@Composable
//fun AddItemScreenPreview() {
//    val viewModel: ProductViewModel = viewModel()
//    AddItemScreen(viewModel = viewModel, navController = rememberNavController())
//}
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.grace.inventory.data.Product
import com.grace.inventory.models.ProductViewModel
import com.grace.inventory.ui.screens.Dashboard.ROUTE_ADD_ITEM

import com.grace.inventory.ui.screens.Dashboard.ROUTE_PRODUCT_LIST
import com.grace.inventory.ui.screens.Dashboard.neonGlow

@Composable
fun AddItemScreen(viewModel: ProductViewModel, navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Black, Color(0xFF0F0F3E), Color(0xFF001133)),
                    start = androidx.compose.ui.geometry.Offset(0f, 0f),
                    end = androidx.compose.ui.geometry.Offset(1000f, 2000f)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ADD NEW ITEM",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Cyan
            )

            NeonTextField(
                value = name,
                onValueChange = { name = it },
                label = "Product Name"
            )

            NeonTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = "Quantity",
                keyboardType = KeyboardType.Number
            )

            NeonTextField(
                value = price,
                onValueChange = { price = it },
                label = "Price",
                keyboardType = KeyboardType.Number
            )

            NeonButton(
                text = "Add Product",
                icon = Icons.Filled.Add,
                onClick =  {
                    val product = Product(
                        productName = name,
                        quantity = quantity.toLongOrNull() ?: 0,
                        sellingPrice = price.toDoubleOrNull() ?: 0.0
                    )
                    viewModel.addProduct(product)
                    navController.navigate(ROUTE_PRODUCT_LIST)
                },
                backgroundColor = Color.Magenta
            )
        }
    }
}

@Composable
fun NeonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label, color = Color.Cyan)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .neonGlow(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Cyan,
            unfocusedBorderColor = Color.Cyan.copy(alpha = 0.5f),
            focusedLabelColor = Color.Cyan,
            cursorColor = Color.Cyan
        )
    )
}

@Composable
fun NeonButton(
    text: String,
    icon: ImageVector? = null,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .glow(),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = text, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(text = text, color = Color.White, fontWeight = FontWeight.Bold)
    }
}

fun Modifier .glow(color: Color = Color.Cyan): Modifier = this.then(
    Modifier.graphicsLayer {
        shadowElevation = 12f
        shape = RoundedCornerShape(12.dp)
        clip = false
    }.drawBehind {
        drawRect(color.copy(alpha = 0.4f), size = size)
    }
)

@Composable
@Preview
fun AddItemScreenPreview() {
    val viewModel: ProductViewModel = viewModel()
    AddItemScreen(viewModel = viewModel, navController = rememberNavController())
}
