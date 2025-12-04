package com.zulfadar.technicaltest.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.zulfadar.technicaltest.ui.screen.favorite.FavoriteScreen
import com.zulfadar.technicaltest.ui.screen.quoteoftheday.QuoteScreen

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        navigation(
            startDestination = Screens.QuoteOfTheDay.route,
            route = "main"
        ) {
            composable(Screens.QuoteOfTheDay.route) {
                QuoteScreen(
                )
            }
            composable(Screens.Favorite.route) {
                FavoriteScreen()
            }
        }
    }
}