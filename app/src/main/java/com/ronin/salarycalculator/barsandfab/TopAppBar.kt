package com.ronin.salarycalculator.barsandfab

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    navController: NavController,
    iconMenu: Int,
    iconSettings: Int,
    title: String,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors()
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    TopAppBar(
        title = { Text(text = title) },
        colors = colors,
        navigationIcon = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(iconMenu),
                    contentDescription = iconMenu.toString()
                )
            }
        },
        actions = {
            if (currentRoute != "settings") {
                IconButton(
                    onClick = { navController.navigate("settings") }
                ) {
                    Icon(
                        painter = painterResource(iconSettings),
                        contentDescription = iconSettings.toString()
                    )
                }
            }
        }
    )
}