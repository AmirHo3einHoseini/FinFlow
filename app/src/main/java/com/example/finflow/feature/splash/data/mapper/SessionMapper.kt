package com.example.finflow.feature.splash.data.mapper

import com.example.finflow.core.datastore.session.model.StoredSession
import com.example.finflow.feature.splash.domain.model.Session

fun Session.toStoredSession(): StoredSession {
    return StoredSession(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}


fun StoredSession.toSession(): Session {
    return Session(
        accessToken = accessToken,
        refreshToken = refreshToken,
        userId = userId
    )
}