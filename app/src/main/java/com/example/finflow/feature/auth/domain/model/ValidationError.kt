package com.example.finflow.feature.auth.domain.model

enum class ValidationError {
    EMPTY_EMAIL,
    INVALID_EMAIL,
    EMPTY_PASSWORD,
    WEAK_PASSWORD
}