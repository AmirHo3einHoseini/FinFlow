package com.example.finflow.core.data.mapper

import com.example.finflow.core.datastore.session.model.StoredSession
import com.example.finflow.core.domain.model.Session

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