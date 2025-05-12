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
import com.grace.inventory.navigation.ROUTE_DASHBOARD
import com.grace.inventory.navigation.ROUTE_LOGIN


@Composable
fun SplashScreen(navController: NavController
) {
    val alpha = remember { Animatable(0f) }

    // Animate the alpha for fade-in
    LaunchedEffect(true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500)
        )
        delay(2500) // Show splash screen for 2.5 seconds
        navController.navigate(ROUTE_LOGIN) { // Replace with your actual route
            popUpTo(ROUTE_DASHBOARD) { inclusive = true }
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
            text = "Inventory",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.alpha(alpha.value)
        )
    }
}
