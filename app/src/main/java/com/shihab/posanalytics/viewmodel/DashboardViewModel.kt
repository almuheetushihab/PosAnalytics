package com.shihab.posanalytics.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shihab.posanalytics.models.SaleRecord
import com.shihab.posanalytics.models.TimeFilter
import com.shihab.posanalytics.models.TransactionType
import java.time.LocalDate
import java.time.DayOfWeek
import java.time.temporal.TemporalAdjusters

@RequiresApi(Build.VERSION_CODES.O)
class DashboardViewModel : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val today = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    private val yesterday = today.minusDays(1)

    @RequiresApi(Build.VERSION_CODES.O)
    private val twoDaysAgo = today.minusDays(2)

    @RequiresApi(Build.VERSION_CODES.O)
    private val lastWeek = today.minusDays(8)

    @RequiresApi(Build.VERSION_CODES.O)
    private val allSalesData = listOf(
        SaleRecord(amount = 1500.0, date = today, type = TransactionType.SALE),
        SaleRecord(amount = -500.0, date = today, type = TransactionType.REFUND),
        SaleRecord(amount = 3200.0, date = today, type = TransactionType.SALE),

        SaleRecord(amount = 4500.0, date = yesterday, type = TransactionType.SALE),
        SaleRecord(amount = -1000.0, date = yesterday, type = TransactionType.REFUND),

        SaleRecord(amount = 7000.0, date = twoDaysAgo, type = TransactionType.SALE), // This Week

        SaleRecord(
            amount = 12000.0,
            date = lastWeek,
            type = TransactionType.SALE
        ) // All Time (Not this week)
    )

    var selectedFilter by mutableStateOf(TimeFilter.TODAY)

    val filteredSalesList by derivedStateOf {
        val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

        allSalesData.filter { record ->
            when (selectedFilter) {
                TimeFilter.TODAY -> record.date == today
                TimeFilter.YESTERDAY -> record.date == yesterday
                TimeFilter.THIS_WEEK -> !record.date.isBefore(startOfWeek) && !record.date.isAfter(
                    today
                )

                TimeFilter.ALL_TIME -> true
            }
        }
    }

    val grossSale by derivedStateOf {
        filteredSalesList.filter { it.type == TransactionType.SALE }.sumOf { it.amount }
    }

    val totalRefund by derivedStateOf {
        filteredSalesList.filter { it.type == TransactionType.REFUND }.sumOf { it.amount }
    }

    val netSale by derivedStateOf {
        grossSale + totalRefund
    }
}