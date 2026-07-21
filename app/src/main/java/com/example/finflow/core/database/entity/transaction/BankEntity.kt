package com.example.finflow.core.database.entity.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banks")
data class BankEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val logo: String
)
