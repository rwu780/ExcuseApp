package com.rwu780.excuseapp.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rwu780.excuseapp.presentation.excuse_screen.ExcuseScreen
import com.rwu780.excuseapp.presentation.main_screen.MainScreen
import com.rwu780.excuseapp.presentation.util.AppScreens

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = AppScreens.MainScreen.route,
        modifier = modifier
    ) {
        composable(route = AppScreens.MainScreen.route) {
            MainScreen(modifier) { category, number ->
                navController.navigate(
                    AppScreens.ExcuseScreen.createRoute(category, number)
                )
            }
        }

        composable(
            route = AppScreens.ExcuseScreen.route, arguments = listOf(
                navArgument(
                    name = "category",
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(
                    name = "number"
                ) {
                    type = NavType.StringType
                    defaultValue = "1"
                }
            )
        ) {
            ExcuseScreen(modifier) {
                navController.navigateUp()
            }
        }
    }
}