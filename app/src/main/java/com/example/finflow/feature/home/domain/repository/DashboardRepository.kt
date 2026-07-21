package com.example.finflow.feature.home.domain.repository

import com.example.finflow.feature.home.domain.model.DashboardSummary

interface DashboardRepository {

    suspend fun getDashboardSummary(): DashboardSummary
}