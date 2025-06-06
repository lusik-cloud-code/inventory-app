package com.grace.inventory.ui.screens.auth



import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.grace.inventory.navigation.ROUTE_LOGIN
import com.grace.inventory.navigation.ROUTE_SPLASH


@Composable
fun SplashScreen(navController: NavController
) {
    val alpha = remember { Animatable(0f) }

    // Animate the alpha for fade-in

        LaunchedEffect(Unit) {
            delay(2000)
            navController.navigate(ROUTE_LOGIN) {
                popUpTo(ROUTE_SPLASH) { inclusive = true }
            }
        }
    // UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Inventory App",
            fontSize = 100.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.alpha(alpha.value)
        )
    }
}
