package com.zulfadar.technicaltest.data.remote.api

import com.zulfadar.technicaltest.data.remote.response.GetQuoteResponse
import retrofit2.http.GET

interface ApiService {
    @GET("qotd")
    suspend fun getQuoteOfTheDay(): GetQuoteResponse
}