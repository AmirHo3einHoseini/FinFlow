package com.example.finflow.feature.transaction.domain.model

import java.time.LocalDateTime

data class Transaction(
    val id: Long,
    val amount: Long,
    val account: Account,
    val category: Category,
    val type: TransactionType,
    val note: String?,
    val createdAt: LocalDateTime
)
