package com.example.quizz.screens.chat

import kotlinx.serialization.Serializable

@Serializable
data class Message(val name: String, val text: String)
