package com.example.finflow.feature.transaction.domain.model


enum class CategoryType {

    INCOME,
    EXPENSE
}

enum class CategoryIcon {
    FOOD,
    TRANSPORT,
    SALARY,
    CAFE
}

data class Category(
    val id: Long,
    val name: String,
    val icon: CategoryIcon,
    val color: String? = null,
    val isSystem: Boolean,
    val isArchived: Boolean,
    val type: CategoryType,
    val isDefault: Boolean
)
