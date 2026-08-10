package com.example.medical_tab.custom_components

import com.example.medical_tab.ui.EmployeeIDApp
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medical_tab.repository.MedicalRepository
import com.example.medical_tab.ui.MedicalListScreen

@Composable
fun AppNavigation(repository: MedicalRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            EmployeeIDApp(
                repository = repository,
            ) {
                navController.navigate("medical_list")
            }
        }
        composable("medical_list") {
            MedicalListScreen(
                repository = repository,
            ) {
                navController.popBackStack()
            }
        }
    }
}
