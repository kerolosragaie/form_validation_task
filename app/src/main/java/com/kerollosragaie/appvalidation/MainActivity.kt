package com.kerollosragaie.appvalidation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.kerollosragaie.appvalidation.core.navigation.NavigationGraph
import com.kerollosragaie.appvalidation.core.theme.AppValidationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppValidationTheme {
                NavigationGraph(navHostController = navController)
            }
        }
    }
}

