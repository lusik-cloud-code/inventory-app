package com.grace.inventory.ui.theme



import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun InventoryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = DarkPrimary,
            onPrimary = DarkOnPrimary,
            background = DarkBackground,
            onBackground = DarkOnBackground,
            error = DarkError,
            onError = DarkOnBackground
        )
    } else {
        lightColorScheme(
            primary = LightPrimary,
            onPrimary = LightOnPrimary,
            background = LightBackground,
            onBackground = LightOnBackground,
            error = LightError,
            onError = LightOnBackground
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
         // You can define this if needed
        shapes = AppShapes,
        content = content
    )
}
