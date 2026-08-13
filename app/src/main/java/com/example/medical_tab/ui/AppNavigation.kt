package com.example.medical_tab.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medical_tab.repository.MedicalRepository

@Composable
fun AppNavigation(repository: MedicalRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            EmployeeIDApp(
                repository = repository,
                onMenuClick = {
                    navController.navigate("request_list")
                }
            )
        }
        composable("request_list") {
            DailyRequestListScreen(
                repository = repository,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
