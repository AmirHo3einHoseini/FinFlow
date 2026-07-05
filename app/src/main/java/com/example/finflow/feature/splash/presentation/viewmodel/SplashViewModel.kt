package com.example.finflow.feature.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finflow.feature.splash.domain.model.CheckSessionResult
import com.example.finflow.feature.splash.domain.usecase.CheckSessionUseCase
import com.example.finflow.feature.splash.presentation.event.SplashEvent
import com.example.finflow.feature.splash.presentation.state.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkSessionUseCase: CheckSessionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _event = MutableSharedFlow<SplashEvent>()
    val event = _event.asSharedFlow()



    fun checkSession() {
        viewModelScope.launch {

            when (checkSessionUseCase()) {
                CheckSessionResult.Authenticated -> {
                    _event.emit(SplashEvent.NavigateToHome)
                }

                CheckSessionResult.Unauthenticated -> {
                    _event.emit(SplashEvent.NavigateToLogin)

                }
            }
        }

    }
}


