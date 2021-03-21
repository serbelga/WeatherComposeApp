package com.example.androiddevchallenge.ui

import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.ui.Destinations.Settings

object Destinations {
    const val Home = "Home"
    const val Settings = "Settings"
}

class Actions(private val navController: NavController) {
    val navToSettings: () -> Unit = {
        navController.navigate(route = Settings)
    }
    val navigateUp: () -> Unit = {
        navController.popBackStack()
    }
}