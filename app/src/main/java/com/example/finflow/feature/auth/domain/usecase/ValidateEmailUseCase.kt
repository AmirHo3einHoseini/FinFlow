package com.example.finflow.feature.auth.domain.usecase

import android.util.Patterns
import com.example.finflow.feature.auth.domain.model.ValidationError
import com.example.finflow.feature.auth.domain.model.ValidationResult
import java.util.regex.Pattern
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {

    operator fun invoke(email: String): ValidationResult {

        if (email.isBlank()) {
            return ValidationResult.Error(
                ValidationError.EMPTY_EMAIL
            )
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult.Error(
                ValidationError.INVALID_EMAIL
            )
        }
        return ValidationResult.Success
    }
}