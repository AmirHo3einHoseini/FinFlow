package com.example.finflow.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finflow.feature.auth.domain.model.ValidationError
import com.example.finflow.feature.auth.domain.model.ValidationResult
import com.example.finflow.feature.auth.domain.usecase.ValidateEmailUseCase
import com.example.finflow.feature.auth.domain.usecase.ValidatePasswordUseCase
import com.example.finflow.feature.auth.presentation.event.LoginEvent
import com.example.finflow.feature.auth.presentation.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()
    private val _event = MutableSharedFlow<LoginEvent>()
    val event = _event.asSharedFlow()


    fun onEmailChanged(email: String) {

        _uiState.update {
            it.copy(
                email = email,
                emailError = null
            )
        }

    }

    fun onPasswordChanged(password: String) {
        _uiState.update {
            it.copy(
                password = password,
                passwordError = null
            )
        }
    }

    fun onLoginClicked() {
        val emailResult = validateEmailUseCase(uiState.value.email)

        val passwordResult = validatePasswordUseCase(uiState.value.password)

        _uiState.update {
            it.copy(
                emailError = mapEmailError(emailResult),
                passwordError = mapPasswordError(passwordResult)
            )
        }

        if (emailResult is ValidationResult.Success && passwordResult is ValidationResult.Success) {
            viewModelScope.launch {
                _event.emit(LoginEvent.NavigateToHome)
            }
        }
    }

    private fun mapEmailError(result: ValidationResult): String? =
        when (result) {
            ValidationResult.Error(ValidationError.EMPTY_EMAIL) -> {
                "Email is required"
            }

            ValidationResult.Error(ValidationError.INVALID_EMAIL) -> {
                "Enter a Valid Email"
            }

            else -> null

        }


    fun mapPasswordError(validationResult: ValidationResult): String? =

        when (validationResult) {
            ValidationResult.Error(ValidationError.EMPTY_PASSWORD) -> {
                "Password is required"
            }

            ValidationResult.Error(ValidationError.WEAK_PASSWORD) -> {
                "Password must be at least 8 character"
            }

            else -> null
        }


}