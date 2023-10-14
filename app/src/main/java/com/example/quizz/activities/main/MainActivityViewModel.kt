package com.example.quizz.activities.main

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(): ViewModel() {

}

@Composable
fun MyScreen(viewModel: MainActivityViewModel = MainActivityViewModel()) {

}