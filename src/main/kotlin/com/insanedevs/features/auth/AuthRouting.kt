package com.insanedevs.features.auth

import com.insanedevs.features.login.LoginController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAuthRouting() {

    routing {
        post("/auth") {
            val authController = AuthController(call)
            authController.performAuth()

        }
    }
}