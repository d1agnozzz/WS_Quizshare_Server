package com.insanedevs.features.create_quiz

import com.insanedevs.features.login.LoginController
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureCreateQuizRouting() {

    routing {
        post("/quiz/create") {
            val createQuizController = CreateQuizController(call)
            createQuizController.createQuiz()

        }
    }
}