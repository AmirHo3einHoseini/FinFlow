package com.example.finflow.feature.transaction.presentation.event

import com.example.finflow.feature.transaction.domain.model.Account
import com.example.finflow.feature.transaction.domain.model.Category
import com.example.finflow.feature.transaction.domain.model.TransactionType
import java.time.LocalDateTime

sealed interface TransactionEvent {

    data class ChangeType(val type: TransactionType) : TransactionEvent

    data class ChangeAmount(val amount: String) : TransactionEvent

    data class SelectCategory(val category: Category) : TransactionEvent

    data class SelectedAccount(val account: Account) : TransactionEvent

    data class ChangeDate(val dateTime: LocalDateTime) : TransactionEvent

    data class ChangeDescription(val description: String) : TransactionEvent

    data object Save : TransactionEvent

    data object Reset : TransactionEvent

}