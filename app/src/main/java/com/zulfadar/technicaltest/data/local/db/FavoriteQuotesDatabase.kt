package com.zulfadar.technicaltest.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zulfadar.technicaltest.data.local.dao.FavoriteQuotesDao
import com.zulfadar.technicaltest.data.local.model.FavoriteQuotes

@Database(
    entities = [FavoriteQuotes::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteQuotesDatabase: RoomDatabase() {
    abstract fun favoriteQuotesDao(): FavoriteQuotesDao

    companion object {
        @Volatile private var INSTANCE: FavoriteQuotesDatabase? = null

        fun getInstance(context: Context): FavoriteQuotesDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context,
                    FavoriteQuotesDatabase::class.java,
                    "db_favorite_quotes"
                ).build().also { INSTANCE = it }
            }
    }
}