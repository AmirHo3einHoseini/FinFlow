package com.example.finflow.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.finflow.core.database.entity.transaction.TransactionEntity


@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transaction: TransactionEntity): Long

}