package com.grace.inventory.models




import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

import com.grace.inventory.navigation.ROUTE_DASHBOARD
import com.grace.inventory.navigation.ROUTE_LOGIN


class AuthViewModel(
    private val navController: NavHostController,
    private val context: Context
) : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(name: String, email: String, password: String, confirmPassword: String) {
        if (email.isBlank() || password.isBlank() || name.isBlank()) {
            showToast("Please fill in all fields")
            return
        }

        if (password != confirmPassword) {
            showToast("Passwords do not match")
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Registration Successful!")
                    navController.navigate(ROUTE_DASHBOARD)
                } else {
                    showToast("Registration Failed: ${task.exception?.message}")
                }
            }
    }

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            showToast("Please enter all fields")
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("Login Successful")
                    navController.navigate(ROUTE_DASHBOARD)
                } else {
                    showToast("Login Failed: ${task.exception?.message}")
                }
            }
    }
    fun signOut() {
        auth.signOut()
        showToast("Logged out successfully")
        navController.navigate(ROUTE_LOGIN) {
            popUpTo(0) // Clears backstack to prevent returning via back button
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
