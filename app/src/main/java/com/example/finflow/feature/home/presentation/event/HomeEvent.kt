package com.example.finflow.feature.home.presentation.event

sealed interface HomeEvent{
    data object NavigateToTransfer: HomeEvent
    data object NavigateToExpense : HomeEvent
    data object NavigateToIncome : HomeEvent
    data object NavigateToTransaction: HomeEvent
}