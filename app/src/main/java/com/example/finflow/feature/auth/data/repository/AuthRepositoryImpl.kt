package com.example.finflow.feature.auth.data.repository

import com.example.finflow.core.domain.repository.SessionRepository
import com.example.finflow.feature.auth.domain.repository.AuthRepository
import com.example.finflow.feature.auth.domain.model.LoginResult
import com.example.finflow.core.domain.model.Session
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sessionRepository: SessionRepository
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ) {
        val session = Session(
            accessToken = "fake_access_token",
            refreshToken = "fake_refresh_token",
            userId = 1L
        )
        sessionRepository.saveSession(session)
    }
}