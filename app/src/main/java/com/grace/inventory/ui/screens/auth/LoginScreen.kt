package com.grace.inventory.ui.screens.auth

import com.grace.inventory.navigation.ROUTE_REGISTER
//
//
//import android.annotation.SuppressLint
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
//import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.Lock
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.grace.inventory.models.AuthViewModel
//import com.grace.inventory.navigation.ROUTE_SIGN_OUT
//
//
//@SuppressLint("SuspiciousIndentation")
//@Composable
//fun LoginScreen(navController:NavHostController){
//    Column(
//        modifier = Modifier
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Spacer(modifier = Modifier.height(30.dp))
//
//
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        Text(
//            text = "Welcome ",
//            fontSize = 40.sp,
//            fontWeight = FontWeight.Bold,
//            fontFamily = FontFamily.Cursive
//        )
//        Spacer(modifier = Modifier.height(10.dp))
//
//        var email by remember { mutableStateOf("") }
//        var password by remember { mutableStateOf("") }
//
//        OutlinedTextField(
//            value = email,
//            onValueChange = {email = it},
//            label = { Text(text = "Email Address", fontFamily = FontFamily.SansSerif)},
//            textStyle = TextStyle(color = Color.Black),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "")},
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 20.dp, end = 20.dp),
//            shape = RoundedCornerShape(5.dp)
//        )
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//
//        OutlinedTextField(
//            value = password,
//            onValueChange = {password = it},
//            label = { Text(text = "Password", fontFamily = FontFamily.SansSerif)},
//            textStyle = TextStyle(color = Color.Black),
//
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 20.dp, end = 20.dp),
//            shape = RoundedCornerShape(5.dp),
//            visualTransformation = PasswordVisualTransformation()
//
//        )
//
//
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//
//        val context = LocalContext.current
//        val authViewModel = AuthViewModel(navController, context)
//
//        Button(
//            onClick = { authViewModel.login(email, password) },
//            colors = ButtonDefaults.buttonColors(Color.DarkGray),
//            shape = RoundedCornerShape(5.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 20.dp, end = 20.dp),
//        ) {
//            Text(text = "Login", fontFamily = FontFamily.SansSerif)
//        }
//
//
//
//
//
//        Button(
//            onClick = { navController.navigate(ROUTE_REGISTER) },
//            colors = ButtonDefaults.buttonColors(Color.DarkGray),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 20.dp, end = 20.dp),
//            shape = RoundedCornerShape(5.dp)) {
//            Text(text = "Register", fontFamily = FontFamily.SansSerif)
//        }
//
//
//    }
//
//}
//
//@Composable
//@Preview(showBackground = true)
//fun LoginScreenPreview(){
//    LoginScreen(navController = rememberNavController())
//
//}



import com.grace.inventory.navigation.ROUTE_REGISTER
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.grace.inventory.models.AuthViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(navController: NavHostController) {
    val deepBlue = Color(0xFF0D1B2A)
    val vibrantPurple = Color(0xFF7F00FF)
    val softPurple = Color(0xFF9C27B0)
    val white = Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(deepBlue)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            color = vibrantPurple
        )

        Spacer(modifier = Modifier.height(30.dp))

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text("Email Address", color = softPurple)
            },
            textStyle = TextStyle(color = white),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = softPurple)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = vibrantPurple,
                unfocusedBorderColor = softPurple,
                cursorColor = vibrantPurple
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text("Password", color = softPurple)
            },
            textStyle = TextStyle(color = white),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = null, tint = softPurple)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = vibrantPurple,
                unfocusedBorderColor = softPurple,
                cursorColor = vibrantPurple
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)

        Button(
            onClick = { authViewModel.login(email, password) },
            colors = ButtonDefaults.buttonColors(
                containerColor = vibrantPurple,
                contentColor = white
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Login", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Button(
            onClick = { navController.navigate(ROUTE_REGISTER) },
            colors = ButtonDefaults.buttonColors(
                containerColor = softPurple,
                contentColor = white
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Register", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
