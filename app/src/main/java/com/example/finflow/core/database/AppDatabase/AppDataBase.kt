package com.example.finflow.core.database.AppDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.finflow.core.database.converter.DateTimeConverter
import com.example.finflow.core.database.dao.AccountDao
import com.example.finflow.core.database.dao.BankDao
import com.example.finflow.core.database.dao.CategoryDao
import com.example.finflow.core.database.dao.TransactionDao
import com.example.finflow.core.database.entity.transaction.AccountEntity
import com.example.finflow.core.database.entity.transaction.BankEntity
import com.example.finflow.core.database.entity.transaction.CategoryEntity
import com.example.finflow.core.database.entity.transaction.TransactionEntity

@Database(
    entities = [
        BankEntity::class,
        AccountEntity::class,
        CategoryEntity::class,
        TransactionEntity::class
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(DateTimeConverter::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun bankDao(): BankDao

    abstract fun accountDao(): AccountDao

    abstract fun categoryDao(): CategoryDao

    abstract fun transactionDao(): TransactionDao

}