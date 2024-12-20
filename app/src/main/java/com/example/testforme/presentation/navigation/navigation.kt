package com.example.testforme.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.testforme.presentation.screens.SignIn.PageAfterSignIn
import com.example.testforme.presentation.screens.SignIn.SignInView
import com.example.testforme.presentation.screens.singup.PageAfterSignUp
import com.example.testforme.presentation.screens.singup.SingUp


@Composable
fun Navigation(controller: NavHostController){

    NavHost(
        startDestination = navigationroutes.SIGNIN,
        navController = controller
    )
    {
        composable(navigationroutes.SIGNUP)
        { SingUp(controller) }
        composable(navigationroutes.PAGEAFTERSIGNUP)
        { PageAfterSignUp(controller) }
        composable(navigationroutes.SIGNIN)
        { SignInView(controller) }
        composable(navigationroutes.PAGEAFTERSIGNIN)
        { PageAfterSignIn(controller) }

    }
}