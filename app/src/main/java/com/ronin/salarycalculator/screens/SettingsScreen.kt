package com.ronin.salarycalculator.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.ronin.salarycalculator.R
import com.ronin.salarycalculator.barsandfab.CustomBottomAppbar
import com.ronin.salarycalculator.barsandfab.CustomTopAppBar

@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                iconMenu = R.drawable.baseline_menu_24,
                iconSettings = R.drawable.baseline_build_24,
                title = "Settings",
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            CustomBottomAppbar(
                navController = navController,
                icon1 = R.drawable.baseline_home_24,
                icon2 = R.drawable.baseline_history_24,
                icon3 = R.drawable.baseline_people_alt_24,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Text(
                text = "Settings Screen"
            )
        }
    }
}