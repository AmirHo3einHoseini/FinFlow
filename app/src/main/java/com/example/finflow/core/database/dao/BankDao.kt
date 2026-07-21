package com.example.finflow.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.finflow.core.database.entity.transaction.BankEntity


@Dao
interface BankDao {

    @Insert
    suspend fun insertAll(
        banks: List<BankEntity>
    )
}