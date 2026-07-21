package com.example.finflow.feature.home.data.repository

import com.example.finflow.feature.home.domain.model.DashboardSummary
import com.example.finflow.feature.home.domain.repository.DashboardRepository
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor() : DashboardRepository {


    override suspend fun getDashboardSummary(): DashboardSummary {
        return DashboardSummary(
            totalBalance = 25_000_000
        )
    }
}