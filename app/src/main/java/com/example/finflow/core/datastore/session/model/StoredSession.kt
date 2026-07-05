package com.example.finflow.core.datastore.session.model

data class StoredSession(
    val accessToken: String,
    val refreshToken: String,
    val userId: Long
)
