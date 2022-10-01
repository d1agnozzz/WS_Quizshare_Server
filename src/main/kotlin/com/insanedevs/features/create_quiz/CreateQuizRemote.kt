package com.insanedevs.features.create_quiz

import com.insanedevs.database.quiz.QuizType
import kotlinx.serialization.Serializable

@Serializable
data class CreateQuizReceiveRemote(
    val creatorEmail: String,
    val group: String?,
    val title: String,
    val description: String,
    val quizType: String
)

@Serializable
data class CreateQuizResponseRemote(
    val id: Int
)