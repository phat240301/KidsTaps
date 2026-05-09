package com.kidstap.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kidstap.app.ui.home.HomeScreen
import com.kidstap.app.ui.setup.GameSetupScreen
import com.kidstap.app.ui.countdown.CountdownScreen
import com.kidstap.app.ui.game.GameScreen
import com.kidstap.app.ui.result.ResultScreen
import com.kidstap.app.ui.settings.SettingsScreen

import com.kidstap.app.ui.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Splash.route,
    ) {
        composable(Route.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(Route.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Route.Setup.route) { backStackEntry ->
            val mode = backStackEntry.arguments?.getString("mode") ?: "shapes"
            GameSetupScreen(navController = navController, mode = mode)
        }

        composable(Route.Countdown.route) {
            CountdownScreen(navController = navController)
        }

        composable(Route.Game.route) {
            GameScreen(navController = navController)
        }

        composable(Route.Result.route) {
            ResultScreen(navController = navController)
        }

        composable(Route.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}
