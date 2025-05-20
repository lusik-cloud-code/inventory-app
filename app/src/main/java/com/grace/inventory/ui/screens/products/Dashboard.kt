package com.grace.inventory.ui.screens.Dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.grace.inventory.navigation.ROUTE_ABOUT
import com.grace.inventory.navigation.ROUTE_ADD_ITEM
import com.grace.inventory.navigation.ROUTE_CURRENT_STOCK
import com.grace.inventory.navigation.ROUTE_EXPENSES
import com.grace.inventory.navigation.ROUTE_PRODUCT_LIST
import com.grace.inventory.navigation.ROUTE_PROFIT_LOSS
import com.grace.inventory.navigation.ROUTE_SALES_ENTRY
import com.grace.inventory.navigation.ROUTE_SIGN_OUT


//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.List
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material.icons.filled.ShoppingCart
//import androidx.compose.material.icons.filled.Star
//import androidx.compose.material.icons.filled.ThumbUp
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.grace.inventory.ui.theme.LightPrimary
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.ui.graphics.Color
//
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.text.font.FontFamily
//
//import com.grace.inventory.navigation.ROUTE_ABOUT
//import com.grace.inventory.navigation.ROUTE_ADD_ITEM
//import com.grace.inventory.navigation.ROUTE_CURRENT_STOCK
//import com.grace.inventory.navigation.ROUTE_EXPENSES
//import com.grace.inventory.navigation.ROUTE_PRODUCT_LIST
//import com.grace.inventory.navigation.ROUTE_PROFIT_LOSS
//import com.grace.inventory.navigation.ROUTE_REGISTER
//import com.grace.inventory.navigation.ROUTE_SALES_ENTRY
//import com.grace.inventory.navigation.ROUTE_SIGN_OUT
//
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Icon
//
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//
//
///**
// * Dashboard screen showing inventory management options
// */
//@Composable
//fun DashboardScreen(navController: NavController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "DASHBOARD",
//            style = MaterialTheme.typography.headlineMedium,
//            fontWeight = FontWeight.Bold,
//            color = LightPrimary,
//            modifier = Modifier
//                .padding(bottom = 16.dp)
//                .align(Alignment.CenterHorizontally)
//        )
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Column(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            // First row of dashboard buttons
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                DashboardButton(
//                    label = "PRODUCTS LIST",
//                    icon = Icons.Filled.List
//                ) {
//                    navController.navigate(ROUTE_PRODUCT_LIST)
//                }
//
//                DashboardButton(
//                    label = "EXPENSES",
//                    icon = Icons.Filled.MoreVert
//                ) {
//                    navController.navigate(ROUTE_EXPENSES)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Second row of dashboard buttons
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                DashboardButton(
//                    label = "ADD ITEM",
//                    icon = Icons.Filled.Add
//                ) {
//                    navController.navigate(ROUTE_ADD_ITEM)
//                }
//
//                DashboardButton(
//                    label = "CURRENT STOCK",
//                    icon = Icons.Filled.Home
//                ) {
//                    navController.navigate(ROUTE_CURRENT_STOCK)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Third row of dashboard buttons
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                DashboardButton(
//                    label = "SALES ENTRY",
//                    icon = Icons.Filled.ShoppingCart
//                ) {
//                    navController.navigate(ROUTE_SALES_ENTRY)
//                }
//
//                DashboardButton(
//                    label = "PROFIT & LOSS",
//                    icon = Icons.Filled.ThumbUp
//                ) {
//                    navController.navigate(ROUTE_PROFIT_LOSS)
//                }
//            }
//
//            // Fourth row with a single button
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                DashboardButton(
//                    label = "ABOUT",
//                    icon = Icons.Filled.Star
//                ) {
//                    navController.navigate(ROUTE_ABOUT)
//                }
//            }
//
//            // Sign out button
//            Button(
//                onClick = { navController.navigate(ROUTE_SIGN_OUT) },
//                colors = ButtonDefaults.buttonColors(Color.DarkGray),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(start = 20.dp, end = 20.dp),
//                shape = RoundedCornerShape(5.dp)
//            ) {
//                Text(
//                    text = "Sign Out",
//                    fontFamily = FontFamily.SansSerif
//                )
//            }
//        }
//    }
//}
//
///**
// * Reusable dashboard button component with icon and label
// */
//@Composable
//fun DashboardButton(
//    label: String,
//    icon: ImageVector,
//    onClick: () -> Unit
//) {
//    Surface(
//        shape = RoundedCornerShape(16.dp),
//        shadowElevation = 4.dp,
//        color = LightPrimary,
//        modifier = Modifier
//            .size(140.dp)
//            .clickable { onClick() }
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(12.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Icon(
//                imageVector = icon,
//                contentDescription = label,
//                tint = MaterialTheme.colorScheme.onPrimary,
//                modifier = Modifier.size(32.dp)
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Text(
//                text = label,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colorScheme.onPrimary,
//                fontSize = 13.sp
//            )
//        }
//    }
//}
//
//@Composable
//@Preview(showBackground = true)
//fun DashboardScreenPreview() {
//    DashboardScreen(navController = rememberNavController())
//}
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.remember
// ---- Route Constants (make sure they match your actual routes) ----
const val ROUTE_PRODUCT_LIST = "product_list"
const val ROUTE_EXPENSES = "expenses"
const val ROUTE_ADD_ITEM = "add_item"
const val ROUTE_CURRENT_STOCK = "current_stock"
const val ROUTE_SALES_ENTRY = "sales_entry"
const val ROUTE_PROFIT_LOSS = "profit_loss"
const val ROUTE_ABOUT = "about"
const val ROUTE_SIGN_OUT = "sign_out"

// ---- Galaxy Background ----
@Composable
fun GalaxyBackground(content: @Composable () -> Unit) {
    val gradient = remember {
        Brush.linearGradient(
            colors = listOf(Color.Black, Color.DarkGray, Color.Blue),
            start = Offset(0f, 0f),
            end = Offset(1000f, 1000f)
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient)
    ) {
        content()
    }
}

// ---- Dashboard Screen ----
@Composable
fun DashboardScreen(navController: NavController) {
    GalaxyBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "DASHBOARD",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Cyan,
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Button Rows
            listOf(
                listOf(
                    Triple("PRODUCTS LIST", Icons.Filled.List, ROUTE_PRODUCT_LIST),
                    Triple("EXPENSES", Icons.Filled.MoreVert, ROUTE_EXPENSES)
                ),
                listOf(
                    Triple("ADD ITEM", Icons.Filled.Add, ROUTE_ADD_ITEM),
                    Triple("CURRENT STOCK", Icons.Filled.Home, ROUTE_CURRENT_STOCK)
                ),
                listOf(
                    Triple("SALES ENTRY", Icons.Filled.ShoppingCart, ROUTE_SALES_ENTRY),
                    Triple("PROFIT & LOSS", Icons.Filled.ThumbUp, ROUTE_PROFIT_LOSS)
                ),
                listOf(
                    Triple("ABOUT", Icons.Filled.Star, ROUTE_ABOUT)
                )
            ).forEach { rowItems ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    rowItems.forEach { (label, icon, route) ->
                        NeonDashboardButton(label, icon) {
                            navController.navigate(route)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Neon Sign Out Button
            NeonButton(
                text = "Sign Out",
                onClick = { navController.navigate(ROUTE_SIGN_OUT) },
                backgroundColor = Color.Magenta
            )
        }
    }
}

// ---- Neon Dashboard Button ----
@Composable
fun NeonDashboardButton(label: String, icon: ImageVector, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 8.dp,
        color = Color.Transparent,
        modifier = Modifier
            .size(140.dp)
            .clickable { onClick() }
            .neonGlow()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.Cyan,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                color = Color.Cyan,
                fontSize = 13.sp
            )
        }
    }
}

// ---- Neon Glow Modifier ----
fun Modifier.neonGlow(): Modifier = this
    .graphicsLayer {
        shadowElevation = 12f
        shape = RoundedCornerShape(16.dp)
        clip = true
    }
    .drawBehind {
        drawRect(
            color = Color.Cyan.copy(alpha = 0.4f),
            size = size
        )
    }

// ---- Neon Button ----
@Composable
fun NeonButton(
    text: String,
    onClick: () -> Unit,
    backgroundColor: Color
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .padding(top = 16.dp)
            .height(50.dp)
            .width(200.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

