package com.example.finflow.feature.auth.domain.usecase

import android.util.Log
import com.example.finflow.feature.auth.domain.repository.AuthRepository
import com.example.finflow.feature.auth.domain.model.LoginResult
import com.example.finflow.feature.auth.domain.model.ValidationResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) {


    suspend operator fun invoke(
        email: String,
        password: String
    ): LoginResult {
        val emailResult = validateEmailUseCase(email)

        val passwordResult = validatePasswordUseCase(password)

        if (emailResult is ValidationResult.Error) {
            return LoginResult.ValidationFailed(emailResult.error)
        }
        if (passwordResult is ValidationResult.Error) {
            return LoginResult.ValidationFailed(passwordResult.error)
        }

        if (emailResult is ValidationResult.Success && passwordResult is ValidationResult.Success) {
            authRepository.login(email, password)

        }

        return LoginResult.Success
    }
}