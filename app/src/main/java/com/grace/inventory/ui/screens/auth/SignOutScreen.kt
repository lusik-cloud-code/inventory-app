package com.grace.inventory.ui.screens.auth

import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.grace.inventory.models.ProductViewModel
import com.grace.inventory.navigation.ROUTE_LOGIN

@Composable
fun SignOutScreen( viewModel: ProductViewModel, navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Are you sure you want to sign out?",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate(ROUTE_LOGIN) {
                        popUpTo(0) // Clears backstack
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Out")
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = {
                    navController.popBackStack() // Go back to previous screen
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel")
            }
        }
    }
}
