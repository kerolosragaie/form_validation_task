package com.kerollosragaie.appvalidation.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kerollosragaie.appvalidation.features.auth.presentation.signin.SignInScreen
import com.kerollosragaie.appvalidation.features.auth.presentation.signup.SignUpScreen

@Composable
fun NavigationGraph(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.SignInScreen.route,
    ) {
        composable(
            route = Screen.SignInScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
        ) {
            SignInScreen {
                navHostController.navigate(Screen.SignUpScreen.route)
            }
        }

        composable(
            route = Screen.SignUpScreen.route,
        ) {

            SignUpScreen() {
                navHostController.popBackStack()
            }
        }
    }
}