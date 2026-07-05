package com.example.finflow.feature.splash.data.repository

import com.example.finflow.core.datastore.session.SessionDataStore
import com.example.finflow.feature.splash.data.mapper.toSession
import com.example.finflow.feature.splash.data.mapper.toStoredSession
import com.example.finflow.feature.splash.domain.model.Session
import com.example.finflow.feature.splash.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(
    private val sessionDataStore: SessionDataStore
) : SessionRepository {


    override fun observeSession(): Flow<Session?> {
        return sessionDataStore
            .observeSession()
            .map { storedSession ->
                storedSession?.toSession()
            }
    }

    override suspend fun saveSession(session: Session) {
        sessionDataStore.saveSession(
            session.toStoredSession()
        )
    }

    override suspend fun clearSession() {
        sessionDataStore.clearSession()
    }
}