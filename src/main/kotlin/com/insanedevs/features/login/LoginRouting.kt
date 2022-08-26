package com.insanedevs.features.login

import com.insanedevs.features.LoginReceiveRemote
import com.insanedevs.features.LoginResponseRemote
import com.insanedevs.cache.InMemoryCache
import com.insanedevs.cache.TokenCache
import com.insanedevs.features.register.RegisterReceiveRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

fun Application.configureLoginRouting() {

    routing {
        post("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()

        }
    }
}