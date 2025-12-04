package com.zulfadar.technicaltest.di

import android.content.Context
import com.zulfadar.technicaltest.data.local.db.FavoriteQuotesDatabase
import com.zulfadar.technicaltest.data.remote.api.ApiConfig
import com.zulfadar.technicaltest.data.repository.FavoriteQuotesRepository
import com.zulfadar.technicaltest.data.repository.QuoteRepository

object Injection {
    fun provideQuoteRepository(): QuoteRepository {
        val api = ApiConfig.getQouteApiService()
        return QuoteRepository.getInstance(api)
    }

    fun provideFavoriteRepository(context: Context): FavoriteQuotesRepository {
        val dao = FavoriteQuotesDatabase.getInstance(context).favoriteQuotesDao()
        return FavoriteQuotesRepository.getInstance(dao)
    }
}