package com.shihab.posanalytics.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shihab.posanalytics.models.TimeFilter
import com.shihab.posanalytics.models.TransactionType
import com.shihab.posanalytics.viewmodel.DashboardViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel = viewModel()) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("Analytics Dashboard", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        // ১. Time Filter (Segmented Buttons)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TimeFilter.values().forEach { filter ->
                FilterChip(
                    selected = viewModel.selectedFilter == filter,
                    onClick = { viewModel.selectedFilter = filter },
                    label = { Text(filter.name.replace("_", " ")) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ২. KPI Widgets (3 Cards)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // Gross Sale Card
            KpiCard(
                title = "Gross Sale",
                amount = viewModel.grossSale,
                color = Color(0xFF1E88E5),
                modifier = Modifier.weight(1f)
            )
            // Refund Card
            KpiCard(
                title = "Refunds",
                amount = viewModel.totalRefund,
                color = Color(0xFFE53935),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Net Sale Card (Full width)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2E7D32))
        ) {
            Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Net Sale", color = Color.White, fontSize = 16.sp)
                Text("৳${viewModel.netSale}", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(thickness = 2.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // ৩. Transaction List
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Recent Transactions", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("${viewModel.filteredSalesList.size} Records", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.filteredSalesList) { record ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            val icon = if (record.type == TransactionType.SALE) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward
                            val iconTint = if (record.type == TransactionType.SALE) Color(0xFF2E7D32) else Color(0xFFE53935)

                            Icon(icon, contentDescription = null, tint = iconTint)
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text("INV-${record.id}", fontWeight = FontWeight.Bold)
                                Text("${record.date}", color = Color.Gray, fontSize = 12.sp)
                            }
                        }

                        Text(
                            text = "৳${record.amount}",
                            fontWeight = FontWeight.Bold,
                            color = if (record.type == TransactionType.SALE) Color(0xFF2E7D32) else Color(0xFFE53935)
                        )
                    }
                }
            }
        }
    }
}

// রিইউজেবল উইজেট কার্ড ফাংশন
@Composable
fun KpiCard(title: String, amount: Double, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, color = color, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("৳${amount}", color = color, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashboardPreview() {
    MaterialTheme { DashboardScreen() }
}