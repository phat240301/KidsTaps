package com.kidstap.app.ui.navigation

sealed class Route(val route: String) {
    object Splash : Route("splash")
    object Home : Route("home")
    object Setup : Route("setup/{mode}") {
        fun createRoute(mode: String) = "setup/$mode"
    }
    object Countdown : Route("countdown")
    object Game : Route("game")
    object Result : Route("result")
    object Settings : Route("settings")
}
