package com.example.finflow.feature.transaction.presentation.ui

import com.example.finflow.databinding.TransactionCategoryItemBinding
import com.example.finflow.feature.transaction.domain.model.Category

data class CategoryItem(
    val binding: TransactionCategoryItemBinding,
    val category: Category
)
