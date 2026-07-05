package com.example.finflow.feature.splash.presentation.state

sealed interface SplashUiState {
    data object Loading : SplashUiState
    data class Error(val message: String) : SplashUiState
}