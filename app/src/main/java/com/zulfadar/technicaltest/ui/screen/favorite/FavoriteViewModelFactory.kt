package com.zulfadar.technicaltest.ui.screen.favorite

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zulfadar.technicaltest.di.Injection

class FavoriteViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val favoriteRepo = Injection.provideFavoriteRepository(context)

        return FavoriteViewModel(favoriteRepo) as T
    }
}