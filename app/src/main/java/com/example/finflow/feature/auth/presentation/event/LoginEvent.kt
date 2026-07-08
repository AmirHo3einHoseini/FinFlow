package com.example.finflow.feature.auth.presentation.event

sealed interface LoginEvent {
    data object NavigateToHome : LoginEvent
}