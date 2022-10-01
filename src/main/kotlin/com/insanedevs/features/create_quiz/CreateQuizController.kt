package com.insanedevs.features.create_quiz


import com.insanedevs.database.quiz.Quiz
import com.insanedevs.database.quiz.QuizDTO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.io.IOException


class CreateQuizController(private val call: ApplicationCall) {
    suspend fun createQuiz() {
        try {
            val receive = call.receive<CreateQuizReceiveRemote>()


            val id = Quiz.insertAndGetId(
                QuizDTO(
                    id = null,
                    creatorEmail = receive.creatorEmail,
                    group = if (receive.group != null && !receive.group.isBlank()) {
                        receive.group
                    } else {
                        null
                    },
                    title = receive.title,
                    description = receive.description,
                    quizType = receive.quizType,
                )
            )
            call.respond(CreateQuizResponseRemote(id))
        } catch (e: IOException) {
            call.respond(HttpStatusCode.BadRequest, "Wrong json")
        }
    }
}