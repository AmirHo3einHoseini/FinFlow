package com.example.finflow.feature.splash.domain.repository

import com.example.finflow.feature.splash.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    fun observeSession(): Flow<Session?>

    suspend fun saveSession(session: Session)

    suspend fun clearSession()

}