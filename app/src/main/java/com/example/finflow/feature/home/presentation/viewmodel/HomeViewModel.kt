package com.example.finflow.feature.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finflow.feature.auth.presentation.event.LoginEvent
import com.example.finflow.feature.home.domain.usecase.GetDashboardSummaryUseCase
import com.example.finflow.feature.home.presentation.event.HomeEvent
import com.example.finflow.feature.home.presentation.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dashboardSummaryUseCase: GetDashboardSummaryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    init {
        loadDashboard()
    }


    private fun loadDashboard() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true
                )
            }
            val dashboard = dashboardSummaryUseCase()
            _uiState.update {
                it.copy(
                    isLoading = false,
                    dashboard = dashboard
                )
            }
        }
    }


    private fun addTransactionClicked() {
        viewModelScope.launch {
            _event.emit(HomeEvent.NavigateToTransaction)
        }
    }

}