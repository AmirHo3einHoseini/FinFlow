package com.example.finflow.feature.splash.presentation.event

sealed interface SplashEvent {

    data object NavigateToHome : SplashEvent
    data object NavigateToLogin : SplashEvent
    data object NavigateToOnboarding : SplashEvent

}