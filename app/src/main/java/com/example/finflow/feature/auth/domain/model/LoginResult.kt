package com.example.finflow.feature.auth.domain.model

sealed interface LoginResult {
    data object Success : LoginResult
    data class ValidationFailed(val error: ValidationError) : LoginResult
    data class LoginFailed(val message : String) : LoginResult
    data class Error(val message: String) : LoginResult
}