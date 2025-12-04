package com.zulfadar.technicaltest.ui.navigation

sealed class Screens(val route: String) {
    data object QuoteOfTheDay : Screens("quotescreen")
    data object Favorite : Screens("favoritescreen")
}