package com.example.finflow.core.domain.repository

import com.example.finflow.core.domain.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {

    fun observeSession(): Flow<Session?>

    suspend fun saveSession(session: Session)

    suspend fun clearSession()

}