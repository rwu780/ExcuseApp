package com.rwu780.excuseapp.presentation.util

sealed class AppScreens(val route: String) {
    object MainScreen : AppScreens("main_screen")
    object ExcuseScreen: AppScreens("excuse_screen?category={category}&number={number}") {
        fun createRoute(category: String, number: String) = "excuse_screen?category=$category&number=$number"
    }
}
