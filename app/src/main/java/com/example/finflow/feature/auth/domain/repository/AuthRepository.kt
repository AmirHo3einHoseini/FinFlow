package com.example.finflow.feature.auth.domain.repository

import com.example.finflow.feature.auth.domain.model.LoginResult

interface AuthRepository {

    suspend fun login(email: String, password: String)
}