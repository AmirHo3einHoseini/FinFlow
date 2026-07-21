package com.example.finflow.feature.transaction.presentation.viewmodel

import android.R
import android.R.attr.name
import android.text.TextUtils.isEmpty
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finflow.feature.transaction.domain.model.Category
import com.example.finflow.feature.transaction.domain.model.CategoryIcon
import com.example.finflow.feature.transaction.domain.model.CategoryType
import com.example.finflow.feature.transaction.domain.model.TransactionType
import com.example.finflow.feature.transaction.presentation.event.TransactionEvent
import com.example.finflow.feature.transaction.presentation.state.TransactionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionUiState())
    val uiState: StateFlow<TransactionUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<TransactionEvent>()
    val event = _event.asSharedFlow()
    val pinnedCategories = listOf(
        Category(
            id = 1,
            name = "food",
            icon = CategoryIcon.FOOD,
            type = CategoryType.EXPENSE,
            isArchived = false,
            isSystem = true,
            isDefault = true,
        ),
        Category(
            id = 2,
            name = "Transport",
            icon = CategoryIcon.TRANSPORT,
            type = CategoryType.EXPENSE,
            isArchived = false,
            isSystem = true,
            isDefault = true,
        ), Category(
            id = 3,
            name = "Salary",
            icon = CategoryIcon.SALARY,
            type = CategoryType.INCOME,
            isArchived = false,
            isSystem = true,
            isDefault = true,
        ),
        Category(
            id = 4,
            name = "cafe",
            icon = CategoryIcon.CAFE,
            type = CategoryType.EXPENSE,
            isArchived = false,
            isSystem = true,
            isDefault = true,
        )
    )

    init {
        _uiState.update {
            it.copy(
                pinnedCategories = pinnedCategories
            )
        }
    }

    fun onAmountChanged(amount: String) {
        _uiState.update {
            it.copy(
                amount = amount
            )
        }
    }


    fun transactionTypeChanged(
        type: TransactionType
    ) {
        _uiState.update {
            it.copy(
                transactionType = type
            )
        }
    }

    fun validation() {
        val amount = _uiState.value.amount
        when {
            amount.isBlank() -> {
                _uiState.update {
                    it.copy(
                        amountError = "لطفا مبلغ را وارد کنید"
                    )
                }
            }

            amount.toLongOrNull() == null -> {
                _uiState.update {
                    it.copy(
                        amountError = "مبلغ نامعتبر"
                    )
                }
            }

            amount.toLong() <= 0 -> {
                _uiState.update {
                    it.copy(
                        amountError = "مبلغ باید بیشتر از صفر باشد"
                    )
                }
            }

            else -> {
                _uiState.update {
                    it.copy(
                        amountError = null
                    )
                }
            }
        }
    }

    fun onCategorySelected(category: Category) {
        _uiState.update {
            it.copy(
                selectedCategory = category,
                pinnedCategories = pinnedCategories
            )
        }

    }


}