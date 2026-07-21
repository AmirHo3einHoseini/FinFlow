package com.example.finflow.feature.home.domain.usecase

import com.example.finflow.feature.home.domain.model.DashboardSummary
import com.example.finflow.feature.home.domain.repository.DashboardRepository
import javax.inject.Inject

class GetDashboardSummaryUseCase @Inject constructor(
    private val repository: DashboardRepository
) {
    suspend operator fun invoke(): DashboardSummary {
        return repository.getDashboardSummary()
    }
}