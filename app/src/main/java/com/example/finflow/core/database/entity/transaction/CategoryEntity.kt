package com.example.finflow.core.database.entity.transaction

import android.graphics.drawable.Icon
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finflow.feature.transaction.domain.model.TransactionType

@Entity(
    tableName = "categories"
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val icon: String,
    val color: String,
    val transactionType: TransactionType,
    val isSystem: Boolean,
    val isArchive: Boolean
)
