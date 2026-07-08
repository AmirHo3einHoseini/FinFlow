package com.example.finflow.feature.auth.presentation.state

import android.R

data class LoginUiState(

    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null
)

