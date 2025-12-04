package com.zulfadar.technicaltest.data.repository

import com.zulfadar.technicaltest.data.local.dao.FavoriteQuotesDao
import com.zulfadar.technicaltest.data.local.model.FavoriteQuotes

class FavoriteQuotesRepository(
    private val favoriteQuotesDao: FavoriteQuotesDao
) {
    fun getAllFavorites() = favoriteQuotesDao.getAllFavorites()

    suspend fun addFavorite(quote: FavoriteQuotes) {
        favoriteQuotesDao.insertFavorite(quote)
    }

    suspend fun deleteFavorite(quote: FavoriteQuotes) {
        favoriteQuotesDao.deleteFavorite(quote)
    }

    companion object {
        @Volatile private var instance: FavoriteQuotesRepository? = null

        fun getInstance(dao: FavoriteQuotesDao): FavoriteQuotesRepository =
            instance ?: synchronized(this) {
                instance ?: FavoriteQuotesRepository(dao).also { instance = it }
            }
    }
}
