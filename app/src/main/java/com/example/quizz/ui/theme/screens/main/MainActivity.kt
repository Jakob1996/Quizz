package com.example.quizz.ui.theme.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizz.screens.QuizScreen
import com.example.quizz.screens.StartQuizScreen
import com.example.quizz.ui.theme.QuizzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainActivityViewModel = hiltViewModel()
            QuizzTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val nanController = rememberNavController()
                    AppNavigator(navController = nanController)
                }
            }
        }
    }


    @Composable
    fun AppNavigator(navController: NavHostController) {

        NavHost(
            navController = navController,
            startDestination = Screens.StartQuizScreen.route
        ) {
            composable(Screens.StartQuizScreen.route) {
                StartQuizScreen { navController.navigate(route = Screens.QuizScreen.route) }
            }
            composable(Screens.QuizScreen.route) {
                QuizScreen()
            }
        }
    }
}

sealed class Screens(val route: String) {
    object StartQuizScreen : Screens("startQuizScreen")
    object QuizScreen : Screens("quizScreen")
}