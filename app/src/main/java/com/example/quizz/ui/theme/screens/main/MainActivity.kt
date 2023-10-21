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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizz.screens.chat.ChatScreen
import com.example.quizz.screens.quiz.QuizScreen
import com.example.quizz.screens.generateQuiz.StartQuizScreen
import com.example.quizz.screens.startScreen.StartScreen
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
                    color = MaterialTheme.colorScheme.primary
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
            startDestination = Screens.StartScreen.route
        ) {
            composable(Screens.StartQuizScreen.route) {
                StartQuizScreen { navController.navigate(route = Screens.QuizScreen.route) }
            }
            composable(Screens.QuizScreen.route) {
                QuizScreen()
            }
            composable(Screens.ChatScreen.route) {
                ChatScreen(name = "Jakob")
            }

            composable(Screens.StartScreen.route) {
                StartScreen{
                    when (it) {
                        0 -> navController.navigate(route = Screens.QuizScreen.route)
                        1 -> navController.navigate(route = Screens.ChatScreen.route)
                    }
                }
            }
        }
    }
}

sealed class Screens(val route: String) {
    object StartQuizScreen : Screens("startQuizScreen")
    object QuizScreen : Screens("quizScreen")
    object ChatScreen : Screens("chatScreen")
    object StartScreen : Screens("startScreen")
}