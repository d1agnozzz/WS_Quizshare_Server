package com.insanedevs.database.quiz

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class QuizType(value: String){
    @SerialName("quiz")
    quiz("quiz"),
    @SerialName("test")
    test("test")
}

class QuizDTO(
    val id: Int?,
    val creatorEmail: String,
    val group: String?,
    val title: String,
    val description: String,
    val quizType: String
)