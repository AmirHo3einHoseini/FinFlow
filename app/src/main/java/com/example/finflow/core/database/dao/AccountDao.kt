package com.example.finflow.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.finflow.core.database.entity.transaction.AccountEntity

@Dao
interface AccountDao {

@Insert
suspend fun insert(accountEntity: AccountEntity): Long

}