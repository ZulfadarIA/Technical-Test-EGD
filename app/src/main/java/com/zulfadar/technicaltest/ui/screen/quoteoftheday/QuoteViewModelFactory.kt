package com.zulfadar.technicaltest.ui.screen.quoteoftheday

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zulfadar.technicaltest.di.Injection

class QuoteViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val quoteRepo = Injection.provideQuoteRepository()
        val favoriteRepo = Injection.provideFavoriteRepository(context)
        return QuoteViewModel(quoteRepo, favoriteRepo) as T
    }
}