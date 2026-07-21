package com.example.finflow.feature.home.presentation.state

import com.example.finflow.feature.home.domain.model.DashboardSummary

data class HomeUiState(
    val userName: String = "امیر",
    val totalBalance: Long = 28_540_000,
    val monthlyIncome: Long = 12_500_000,
    val monthlyExpense: Long = 7_200_000,
    val monthlySaving: Long = 5_400_000,
    val isLoading: Boolean = false,
    val isBalanceVisible: Boolean = false,
    val dashboard: DashboardSummary? = null
)