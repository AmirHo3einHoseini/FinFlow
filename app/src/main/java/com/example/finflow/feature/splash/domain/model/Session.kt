package com.example.finflow.feature.splash.domain.model

data class Session(

    val accessToken: String,
    val refreshToken: String,
    val userId: Long
)
