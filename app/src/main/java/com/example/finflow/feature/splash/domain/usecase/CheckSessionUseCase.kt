package com.example.finflow.feature.splash.domain.usecase

import com.example.finflow.feature.splash.domain.model.CheckSessionResult
import com.example.finflow.feature.splash.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(): CheckSessionResult {
        val session = sessionRepository.observeSession().first()

        return if (session == null) {
            CheckSessionResult.Unauthenticated
        } else {
            CheckSessionResult.Authenticated
        }
    }
}
