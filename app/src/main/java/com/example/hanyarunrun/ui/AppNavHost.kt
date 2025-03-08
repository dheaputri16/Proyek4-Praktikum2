package com.example.hanyarunrun.ui

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hanyarunrun.viewmodel.DataViewModel
import kotlinx.coroutines.delay
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.hanyarunrun.ui.screen.SplashScreen
import com.example.hanyarunrun.ui.screen.home.HomeScreen
import com.example.hanyarunrun.viewmodel.DataViewModelFactory

@Composable
fun AppNavHost(viewModel: DataViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("splash") {
                SplashScreen(navigateToHome = {
                    navController.navigate("home") {
                        popUpTo("splash") { inclusive = true } // Hapus splash dari back stack
                    }
                })
            }
            composable("list") { DataListScreen(navController, viewModel) }
            composable("profile") {
                val dataViewModel: DataViewModel = viewModel(factory = DataViewModelFactory(LocalContext.current.applicationContext as Application))
                ProfileScreen(navController, dataViewModel)
            }
            composable("form") { DataEntryScreen(navController, viewModel) }
            composable(
                route = "edit/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id") ?: 0
                EditScreen(navController, viewModel, dataId = id)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("home", Icons.Default.Home, "Home"),
        BottomNavItem("list", Icons.Default.List, "Data List"),
        BottomNavItem("profile", Icons.Default.Person, "Profile")
    )

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(item.label) },
                selected = false,
                onClick = { navController.navigate(item.route) },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) }
            )
        }
    }
}

data class BottomNavItem(val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val label: String)