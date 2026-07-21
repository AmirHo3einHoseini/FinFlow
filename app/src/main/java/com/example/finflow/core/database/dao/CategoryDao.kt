package com.example.finflow.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.finflow.core.database.entity.transaction.CategoryEntity

@Dao
interface CategoryDao {

    @Insert
    suspend fun insertAll(
        categoryEntity: List<CategoryEntity>
    )

}