package com.ronin.salarycalculator.barsandfab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController

@Composable
fun CustomBottomAppbar(
    navController: NavController,
    icon1: Int,
    icon2: Int,
    icon3: Int,
    containerColor: Color,
    contentColor: Color
) {
    BottomAppBar(
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    navController.navigateUp()
                }
            ) {
                Icon(
                    painter = painterResource(icon1),
                    contentDescription = icon1.toString()
                )
            }
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(icon2),
                    contentDescription = icon2.toString()
                )
            }
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(icon3),
                    contentDescription = icon3.toString()
                )
            }
        }
    }
}