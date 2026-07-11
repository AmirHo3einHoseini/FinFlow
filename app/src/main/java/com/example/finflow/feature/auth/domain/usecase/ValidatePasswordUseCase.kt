package com.example.finflow.feature.auth.domain.usecase

import com.example.finflow.feature.auth.domain.model.ValidationError
import com.example.finflow.feature.auth.domain.model.ValidationResult
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor() {


    operator fun invoke(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult.Error(ValidationError.EMPTY_PASSWORD)
        }

        if (password.length < 8) {
        return    ValidationResult.Error(ValidationError.WEAK_PASSWORD)
        }
        return ValidationResult.Success
    }

}