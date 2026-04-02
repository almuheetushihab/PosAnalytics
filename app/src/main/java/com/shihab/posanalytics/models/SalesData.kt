package com.shihab.posanalytics.models

import java.time.LocalDate
import java.util.UUID

enum class TransactionType { SALE, REFUND }

data class SaleRecord(
    val id: String = UUID.randomUUID().toString().take(8),
    val amount: Double,
    val date: LocalDate,
    val type: TransactionType
)

enum class TimeFilter {
    TODAY,
    YESTERDAY,
    THIS_WEEK,
    ALL_TIME
}