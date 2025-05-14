package com.grace.inventory.ui.screens.products

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.grace.inventory.data.Expense
import com.grace.inventory.data.Product
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
@Composable
fun ExpensesScreen(
    expenses: List<Expense>,
    navController: NavController
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var selectedDate by remember { mutableStateOf("") }

    val context = LocalContext.current

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

        Button(onClick = {
            val calendar = Calendar.getInstance()
            DatePickerDialog(
                context,
                { _, y, m, d ->
                    val pickedDate = Calendar.getInstance().apply { set(y, m, d) }
                    selectedDate = dateFormat.format(pickedDate.time)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }) {
            Text(text = if (selectedDate.isNotEmpty()) "Filter: $selectedDate" else "Pick Date")
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


