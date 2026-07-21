package com.example.finflow.core.database.seed

import com.example.finflow.core.database.dao.BankDao
import com.example.finflow.core.database.dao.CategoryDao


class DatabaseSeeder(
    private val bankDao: BankDao,
    private val categoryDao: CategoryDao
) {
}