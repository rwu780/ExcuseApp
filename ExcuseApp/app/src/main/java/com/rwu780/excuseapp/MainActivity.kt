package com.rwu780.excuseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.rwu780.excuseapp.ui.theme.ExcuseAppTheme
import com.rwu780.excuseapp.util.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExcuseAppTheme {
                val navController = rememberNavController()
                AppNavHost(
                    navController = navController,
                )

            }
        }
    }
}

