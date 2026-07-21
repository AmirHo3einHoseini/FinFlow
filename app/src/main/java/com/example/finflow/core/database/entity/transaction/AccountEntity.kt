package com.example.finflow.core.database.entity.transaction

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.finflow.feature.transaction.domain.model.AccountType


@Entity(
    tableName = "accounts",
    foreignKeys = [
        ForeignKey(
            entity = BankEntity::class,
            parentColumns = ["id"],
            childColumns = ["bankId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("bankId")
    ]
)
data class AccountEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val bankId: Long?,
    val type: AccountType,
    val color: String
)
