package com.example.finflow.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finflow.feature.auth.domain.model.LoginResult
import com.example.finflow.feature.auth.domain.model.ValidationError
import com.example.finflow.feature.auth.domain.model.ValidationResult
import com.example.finflow.feature.auth.domain.usecase.LoginUseCase
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
    private val loginUseCase: LoginUseCase
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
        viewModelScope.launch {
            when (val result = loginUseCase(uiState.value.email, uiState.value.password)) {
                LoginResult.Success -> {
                    _event.emit(LoginEvent.NavigateToHome)

                }

                is LoginResult.ValidationFailed -> {
                    _uiState.update {
                        it.copy(
                            emailError = mapEmailError(result.error),
                            passwordError = mapPasswordError(result.error)
                        )
                    }
                }

                is LoginResult.LoginFailed -> {

                }

                else -> {}
            }
        }


    }

    private fun mapEmailError(result: ValidationError): String? =
        when (result) {
            ValidationError.EMPTY_EMAIL ->
                "Email is required"

            ValidationError.INVALID_EMAIL ->
                "Enter a Valid Email"

            else -> null
        }


    fun mapPasswordError(result: ValidationError): String? =
        when (result) {
            ValidationError.EMPTY_PASSWORD ->
                "Password is required"

            ValidationError.WEAK_PASSWORD ->
                "Password must be at least 8 character"

            else -> null
        }

}