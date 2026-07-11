package com.example.finflow.core.domain.model

data class Session(

    val accessToken: String,
    val refreshToken: String,
    val userId: Long
)