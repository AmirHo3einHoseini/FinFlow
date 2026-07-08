package com.example.finflow.feature.auth.domain.model

sealed interface ValidationResult {
    data object Success : ValidationResult
    data class Error(
        val error: ValidationError
    ) : ValidationResult
}
