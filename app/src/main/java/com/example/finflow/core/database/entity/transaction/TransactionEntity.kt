package com.example.finflow.core.database.entity.transaction

import android.R.attr.data
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.RESTRICT
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.finflow.feature.transaction.domain.model.TransactionType
import java.time.LocalDateTime

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = AccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("accountId"),
        Index("categoryId"),
        Index("transactionDate")
    ]
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amount: Long,
    val accountId: Long,
    val categoryId: Long,
    val transactionType: TransactionType,
    val note: String?,
    val transactionDate: LocalDateTime
)
