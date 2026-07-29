package com.ronin.salarycalculator

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ronin.salarycalculator.ui.theme.SalaryCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SalaryCalculatorTheme {
                val context = LocalContext.current
                Scaffold(
                    topBar = {
                        MyTopAppBar(
                            context1 = context,
                            message1 = "You Click on Menu",
                            icon1 = R.drawable.baseline_menu_24,
                            context2 = context,
                            message2 = "You Clicked on Settings",
                            icon2 = R.drawable.baseline_build_24
                        ) },
                    bottomBar = {
                        MyBottomAppBar(
                            context1 = context,
                            message1 = "You Clicked Home",
                            icon1 = R.drawable.baseline_home_24,
                            context2 = context,
                            message2 = "You Clicked History",
                            icon2 = R.drawable.baseline_history_24,
                            context3 = context,
                            message3 = "You Clicked Profile",
                            icon3 = R.drawable.baseline_people_alt_24
                        )
                    },
                    floatingActionButton = {
                        MyFAB(
                            context = context,
                            message = "You Clicked the FAB",
                            icon = R.drawable.baseline_add_24
                        )
                    }
                ) {innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    )
                    CalculatorApp()
                }
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var salary by remember { mutableStateOf("") }
    var daysWorked by remember { mutableStateOf("") }
    var calcSalary by remember { mutableDoubleStateOf(0.0) }
    var isError by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CustomTextField(
            salary,
            onValueChange = {
                salary = it
            },
            label = "Salary",
            leadingIcon = R.drawable.baseline_money_24,
            placeholder = "Salary",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = salary.isNotEmpty() && salary.toDoubleOrNull() == null
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            text = daysWorked,
            onValueChange = { daysWorked = it},
            label = "Days Worked",
            leadingIcon = R.drawable.baseline_calendar_month_24,
            placeholder = 30.toString(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
                        calcSalary = salaryCalculate(salary = parsedSalary, daysWorked = parsedDays)
                    } else {
                        isError = true
                    }
                },
                modifier = Modifier.size(110.dp, height = 40.dp)
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

@Composable
fun CustomTextField(
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: Int,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    isError: Boolean
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null
            )
        },
        label = { Text(text = label) },
        keyboardOptions = keyboardOptions,
        supportingText = {
            if (isError) Text(text = "Type a valid number", color = MaterialTheme.colorScheme.error)
        }
    )
}

/*@Composable
fun ExhibitionText(text: String) {
    Text(
        text = text
    )
}*/

@Composable
fun CustomButton(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = label
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    context1: Context,
    message1: String,
    icon1: Int,
    context2: Context,
    message2: String,
    icon2: Int
    ) {
    TopAppBar(
        title = { Text(text = "Salary Calculator") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.LightGray,
            titleContentColor = Color.DarkGray
        ),
        navigationIcon = {
            IconButton(
                onClick = {
                    Toast.makeText(
                        context1,
                        message1,
                        Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    painter = painterResource(icon1),
                    contentDescription = icon1.toString(),
                    tint = Color.DarkGray
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    Toast.makeText(
                        context2,
                        message2,
                        Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    painter = painterResource(icon2),
                    contentDescription = icon2.toString(),
                    tint = Color.DarkGray

                )
            }
        }
    )
}

@Composable
fun MyBottomAppBar(
    context1: Context,
    message1: String,
    icon1: Int,
    context2: Context,
    message2: String,
    icon2: Int,
    context3: Context,
    message3: String,
    icon3: Int
) {
    BottomAppBar(
        containerColor = Color.LightGray,
        contentColor = Color.DarkGray
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = {
                    Toast.makeText(
                        context1,
                        message1,
                        Toast.LENGTH_SHORT
                    ).show()
                }) {
                Icon(
                    painter = painterResource(icon1),
                    contentDescription = icon1.toString()
                )
            }

            IconButton(
                onClick = {
                    Toast.makeText(
                        context2,
                        message2,
                        Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    painter = painterResource(icon2),
                    contentDescription = icon2.toString()
                )
            }

            IconButton(
                onClick = {
                    Toast.makeText(
                        context3,
                        message3,
                        Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    painter = painterResource(icon3),
                    contentDescription = icon3.toString()
                )
            }
        }
    }
}

@Composable
fun MyFAB(
    context: Context,
    message: String,
    icon: Int
) {
    FloatingActionButton(
        onClick = {
            Toast.makeText(
                context,
                message,
                Toast.LENGTH_SHORT
            ).show()
        },
        containerColor = Color.LightGray,
        contentColor = Color.DarkGray
    ) {
        Icon(
            painter = painterResource(icon),

            contentDescription = icon.toString()
        )
    }
}

@Composable
fun CustomCard(text: String) {
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

fun salaryCalculate(salary: Double, daysWorked: Int = 30): Double {
    return salary / daysWorked
}

/*@Preview
@Composable
fun CustomTextFieldPreview() {
    SalaryCalculatorTheme(
    ) {
        Column {
            CustomTextField(
                text = "Hello",
                onValueChange = { "Test" },
                "Count",
                R.drawable.baseline_money_24,
                "Testing"
            )
            CustomButton(label = "Calculate", onClick = { 1+1})
        }
    }
}*/