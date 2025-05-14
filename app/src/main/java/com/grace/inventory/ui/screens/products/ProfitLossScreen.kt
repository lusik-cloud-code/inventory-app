package com.grace.inventory.ui.screens.products

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePickerDialog
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
import com.grace.inventory.data.SaleTransaction
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.collections.filter

@Composable
fun ProfitAndLossScreen(
    transactions: List<SaleTransaction>,
    expenses: List<Expense>,
    navController: NavController
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var selectedDate by remember { mutableStateOf("") }

    val context = LocalContext.current

    val filteredSales = if (selectedDate.isNotEmpty()) {
        transactions.filter {
            dateFormat.format(Date(it.date)) == selectedDate
        }
    } else transactions

    val filteredExpenses = if (selectedDate.isNotEmpty()) {
        expenses.filter {
            dateFormat.format(Date(it.date)) == selectedDate
        }
    } else expenses

    val totalSales = filteredSales.sumOf { it.totalAmount }
    val totalExpenses = filteredExpenses.sumOf { it.totalPrice }
    val profit = totalSales - totalExpenses

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFEDF4FF))
            .padding(16.dp)
    ) {
        Text("📊 Profit & Loss", fontSize = 24.sp, fontWeight = FontWeight.Bold)

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

        Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFDFFFE2))) {
            Column(Modifier.padding(12.dp)) {
                Text("Total Sales: Ksh $totalSales", fontWeight = FontWeight.SemiBold)
                Text("Total Expenses: Ksh $totalExpenses")
                Text("Profit: Ksh $profit", color = Color(0xFF008000), fontSize = 18.sp)
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Sales Summary", fontWeight = FontWeight.Bold)
        LazyColumn {
            items(filteredSales) {
                Text("• ${it.transactionId} - Ksh ${it.totalAmount} on ${dateFormat.format(Date(it.date))}")
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Expenses Summary", fontWeight = FontWeight.Bold)
        LazyColumn {
            items(filteredExpenses) {
                Text("• ${it.expenseId} - ${it.productName} - Ksh ${it.totalPrice} on ${dateFormat.format(Date(it.date))}")
            }
        }
    }
}
