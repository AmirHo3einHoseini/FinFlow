package com.example.finflow.core.di.database

import android.content.Context
import androidx.room.Insert
import androidx.room.Room
import com.example.finflow.core.database.AppDatabase.AppDataBase
import com.example.finflow.core.database.dao.AccountDao
import com.example.finflow.core.database.dao.BankDao
import com.example.finflow.core.database.dao.CategoryDao
import com.example.finflow.core.database.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {


    @Provides
    @Singleton
    fun provideAppDataBase(
        @ApplicationContext context: Context
    ): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "cashflow.db"
        )
            .build()
    }


    @Provides
    fun provideBankDao(
        appDataBase: AppDataBase
    ): BankDao = appDataBase.bankDao()


    @Provides
    fun provideCategoryDao(
        appDataBase: AppDataBase
    ): CategoryDao = appDataBase.categoryDao()

    @Provides
    fun provideAccountDao(
        appDataBase: AppDataBase
    ): AccountDao = appDataBase.accountDao()


    @Provides
    fun provideTransactionDao(
        appDataBase: AppDataBase
    ): TransactionDao = appDataBase.transactionDao()


}