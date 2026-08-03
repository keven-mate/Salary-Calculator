package com.ronin.salarycalculator.barsandfab

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun CustomFloatingActionButton(
    icon: Int,
    containerColor: Color,
    contentColor: Color
) {
    FloatingActionButton(
        onClick = { /*TODO*/ },
        containerColor = containerColor,
        contentColor = contentColor
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = icon.toString()
        )
    }
}