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
import com.grace.inventory.data.SaleTransaction
import com.grace.inventory.models.ProductViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ProfitAndLossScreen(
    transactions: List<SaleTransaction>,
    expenses: List<Expense>,
    viewModel: ProductViewModel,
    navController: NavController
) {

    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    // Filter by selected date
    val filteredSales = if (selectedDate.isNotEmpty()) {
        transactions.filter {
            dateFormat.format(Date(it.timestamp)) == selectedDate
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
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEDF4FF))
            .padding(16.dp)
    ) {
        Text("📊 Profit & Loss", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))

        Button(onClick = { showDatePicker = true }) {
            Text(text = if (selectedDate.isNotEmpty()) "Filter: $selectedDate" else "Pick Date")
        }

        Spacer(Modifier.height(16.dp))

        // Show Android DatePickerDialog
        if (showDatePicker) {
            AndroidView(factory = {
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val pickedDate = Calendar.getInstance().apply {
                            set(year, month, dayOfMonth)
                        }
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
                View(context) // Dummy view
            })
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFDFFFE2))
        ) {
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
                Text("• ${it.transactionId} - Ksh ${it.totalAmount} on ${dateFormat.format(Date(it.timestamp))}")
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
