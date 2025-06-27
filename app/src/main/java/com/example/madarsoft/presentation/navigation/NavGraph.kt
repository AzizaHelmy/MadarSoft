package com.example.madarsoft.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.madarsoft.presentation.screen.details.DetailsScreen
import com.example.madarsoft.presentation.screen.home.HomeScreen

/**
 * Created by Aziza Helmy on 27/06/2025.
 */

val localNavController = compositionLocalOf<NavHostController> { error("No navController found") }


@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    CompositionLocalProvider(value = localNavController provides navController) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = Destination.Home,
        ) {
            composable<Destination.Home> {
                HomeScreen()
            }
            composable<Destination.Details> {
                DetailsScreen()
            }
        }
    }
}
