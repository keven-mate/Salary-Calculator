package com.ronin.salarycalculator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ronin.salarycalculator.salaryCalculate
import com.ronin.salarycalculator.R
import com.ronin.salarycalculator.barsandfab.CustomBottomAppbar
import com.ronin.salarycalculator.barsandfab.CustomTopAppBar
import com.ronin.salarycalculator.ui.theme.SalaryCalculatorTheme

@Composable
fun HomeScreen(navController: NavController) {
    var salary by remember { mutableStateOf("") }
    var daysWorked by remember { mutableStateOf("") }
    var calcSalary by remember { mutableDoubleStateOf(0.0) }
    var isError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                navController = navController,
                iconMenu = R.drawable.baseline_menu_24,
                title = "Salary Calculator",
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary
                ),
                iconSettings = R.drawable.baseline_build_24
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            CustomTextField(
                salary,
                onValueChange = {
                    salary = it
                },
                label = "Salary",
                leadingIcon = R.drawable.baseline_money_24,
                placeHolder = "Salary",
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
                isError = salary.isNotEmpty() && salary.toDoubleOrNull() == null,
            )

            Spacer(modifier = Modifier.height(15.dp))

            CustomTextField(
                text = daysWorked,
                onValueChange = { daysWorked = it },
                label = "Days Worked",
                leadingIcon = R.drawable.baseline_calendar_month_24,
                placeHolder = 30.toString(),
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
                isError = daysWorked.isNotEmpty() && daysWorked.toIntOrNull() == null
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row {
                CustomButton(
                    label = "Calculate",
                    onClick = {
                        val parsedSalary = salary.toDoubleOrNull()
                        val parsedDays = daysWorked.toIntOrNull()

                        if (parsedSalary != null && parsedDays != null) {
                            calcSalary =
                                salaryCalculate(salary = parsedSalary, daysWorked = parsedDays)
                        } else {
                            isError = true
                        }
                    },
                    modifier = Modifier.size(110.dp, height = 40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )

                Spacer(modifier = Modifier.width(15.dp))

                CustomButton(
                    label = "Reset",
                    onClick = {
                        salary = ""
                        daysWorked = "30"
                    },
                    modifier = Modifier.size(110.dp, height = 40.dp)
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            //ExhibitionText("Salary Per Day: ${salary.toDouble()/30.0}")
            CustomCard("Salary by Worked Days: $calcSalary")
        }
    }
}

@Composable
fun CustomTextField(
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: Int,
    placeHolder: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    isError: Boolean
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeHolder) },
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = leadingIcon.toString()
            )
        },
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        supportingText = {
            if (isError) Text(text = "Type a Valid Number", color = MaterialTheme.colorScheme.error)
        }
    )
}

@Composable
fun CustomButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors()
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = colors
    ) {
        Text(
            text = label
        )
    }
}

@Composable
fun CustomCard(
    text: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SalaryCalculatorTheme {
        val navController = rememberNavController()
        HomeScreen(navController)
    }
}