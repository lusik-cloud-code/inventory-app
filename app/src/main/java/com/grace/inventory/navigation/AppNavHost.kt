package com.grace.inventory.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel


import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grace.inventory.models.ProductViewModel
import com.grace.inventory.ui.screens.DashboardScreen
import com.grace.inventory.ui.screens.auth.LoginScreen
import com.grace.inventory.ui.screens.auth.RegisterScreen
import com.grace.inventory.ui.screens.auth.SignOutScreen
import com.grace.inventory.ui.screens.auth.SplashScreen
import com.grace.inventory.ui.screens.products.AboutCompanyScreen
import com.grace.inventory.ui.screens.products.AddItemScreen
import com.grace.inventory.ui.screens.products.CurrentStockScreen
import com.grace.inventory.ui.screens.products.ProductListScreen
import com.grace.inventory.ui.screens.products.SalesEntryScreen


@Composable
fun AppNavHost(
    navController: NavHostController,

    modifier: Modifier = Modifier
){
    NavHost(navController=navController,
        startDestination = ROUTE_SPLASH
    ) {
        composable(ROUTE_SPLASH)
        {
            SplashScreen(navController)
        }
        composable(ROUTE_DASHBOARD) {
            DashboardScreen(navController)
        }
        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUTE_PRODUCT_LIST) {
            ProductListScreen(viewModel(),navController)
        }
        composable(ROUTE_ADD_ITEM) {
            AddItemScreen(viewModel(), navController)
        }
        composable(ROUTE_CURRENT_STOCK) {
            CurrentStockScreen(viewModel(),navController)
        }
        composable(ROUTE_SALES_ENTRY) {
            SalesEntryScreen(viewModel(), navController)
        }
        composable(ROUTE_SIGN_OUT) {
            SignOutScreen(viewModel(), navController)
        }
        composable(ROUTE_ABOUT) {
            AboutCompanyScreen(navController)
        }




    }
}


