package com.zulfadar.technicaltest.data.repository

import com.zulfadar.technicaltest.data.remote.api.ApiService

class QuoteRepository(
    private val apiService: ApiService
) {
    suspend fun getQuoteOfTheDay() = apiService.getQuoteOfTheDay()

    companion object {
        @Volatile private var instance: QuoteRepository? = null

        fun getInstance(api: ApiService): QuoteRepository =
            instance ?: synchronized(this) {
                instance ?: QuoteRepository(api).also { instance = it }
            }
    }
}