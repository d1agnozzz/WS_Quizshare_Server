package com.insanedevs.features.register

import com.insanedevs.features.LoginReceiveRemote
import com.insanedevs.features.LoginResponseRemote
import com.insanedevs.cache.InMemoryCache
import com.insanedevs.cache.TokenCache
import com.insanedevs.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureRegisterRouting() {

    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()



        }
    }
}

