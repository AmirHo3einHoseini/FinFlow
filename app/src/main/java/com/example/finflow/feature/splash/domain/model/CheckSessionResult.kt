package com.example.finflow.feature.splash.domain.model

sealed interface CheckSessionResult {

    data object Authenticated : CheckSessionResult
    data object Unauthenticated : CheckSessionResult

}