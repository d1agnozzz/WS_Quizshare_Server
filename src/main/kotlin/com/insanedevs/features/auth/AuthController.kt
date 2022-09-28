package com.insanedevs.features.auth

import com.insanedevs.database.tokens.TokenDTO
import com.insanedevs.database.tokens.Tokens
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class AuthController(private val call: ApplicationCall) {
    suspend fun performAuth() {
        val receive = call.receive<AuthReceiveRemote>()

        val tokenDTO = Tokens.fetchToken(receive.token)

        if (tokenDTO == null) {
            call.respond(HttpStatusCode.Conflict, "Token not found")
        } else {
            call.respond(AuthResponseRemote(tokenDTO.email!!))
        }
    }
}