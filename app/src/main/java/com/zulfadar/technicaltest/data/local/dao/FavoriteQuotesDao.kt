package com.zulfadar.technicaltest.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zulfadar.technicaltest.data.local.model.FavoriteQuotes
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteQuotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteQuotes)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteQuotes)

    @Query("SELECT * FROM favorite_quotes ORDER BY savedAt DESC")
    fun getAllFavorites(): Flow<List<FavoriteQuotes>>
}