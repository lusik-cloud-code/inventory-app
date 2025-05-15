package com.grace.inventory.ui.screens.products

import android.app.DatePickerDialog
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.grace.inventory.data.Expense
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ExpensesScreen(
    expenses: List<Expense>,
    navController: NavController
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    val filteredExpenses = if (selectedDate.isNotEmpty()) {
        expenses.filter {
            dateFormat.format(Date(it.date)) == selectedDate
        }
    } else expenses

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF3E0))
            .padding(16.dp)
    ) {
        Text("💸 Expenses", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        Button(onClick = { showDatePicker = true }) {
            Text(text = if (selectedDate.isNotEmpty()) "Filter: $selectedDate" else "Pick Date")
        }

        if (showDatePicker) {
            AndroidView(factory = {
                DatePickerDialog(
                    context,
                    { _, y, m, d ->
                        val pickedDate = Calendar.getInstance().apply { set(y, m, d) }
                        selectedDate = dateFormat.format(pickedDate.time)
                        showDatePicker = false
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).apply {
                    setOnCancelListener { showDatePicker = false }
                    show()
                }
                View(context)
            })
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(filteredExpenses) { expense ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0B2))
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text("Expense ID: ${expense.expenseId}")
                        Text("Product: ${expense.productName}")
                        Text("Price: Ksh ${expense.totalPrice}")
                        Text("Date: ${dateFormat.format(Date(expense.date))}")
                    }
                }
            }
        }
    }
}
