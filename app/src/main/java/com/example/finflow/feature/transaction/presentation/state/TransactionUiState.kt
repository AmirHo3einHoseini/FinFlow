package com.example.finflow.feature.transaction.presentation.state

import com.example.finflow.feature.transaction.domain.model.Account
import com.example.finflow.feature.transaction.domain.model.Category
import com.example.finflow.feature.transaction.domain.model.TransactionType
import java.time.LocalDateTime

data class TransactionUiState(
    val transactionType: TransactionType = TransactionType.INCOME,
    val amount: String = "",
    val amountError: String? = null,
    val selectedCategory: Category? = null,
    val selectedAccount: Account? = null,
    val pinnedCategories: List<Category> = emptyList(),
    val dateTime: LocalDateTime = LocalDateTime.now(),
    val description: String = "",
    val isLoading: Boolean = false
)