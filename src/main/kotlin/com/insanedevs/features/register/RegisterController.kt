package com.insanedevs.features.register

import com.insanedevs.database.tokens.TokenDTO
import com.insanedevs.database.tokens.Tokens
import com.insanedevs.database.users.UserDTO
import com.insanedevs.database.users.Users
import com.insanedevs.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {
        val receive = call.receive<RegisterReceiveRemote>()

        if (!receive.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }

        val userDTO = Users.fetchUser(receive.email)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User with this email already exists")
        } else {


            Users.insert(
                UserDTO(
                    email = receive.email,
                    password = receive.password,
                    name = receive.name,
                    secondName = receive.secondName,
                    patronymicName = receive.patronymicName?.ifEmpty { null },
                )
            )

            var tokenDTO = TokenDTO(
                email = receive.email,
                token = UUID.randomUUID().toString()
            )

            while (Tokens.fetchToken(tokenDTO.token) != null) {
                tokenDTO = TokenDTO(
                    email = receive.email,
                    token = UUID.randomUUID().toString()
                )
            }

            Tokens.insert(
                tokenDTO
            )

            call.respond(RegisterResponseRemote(token = tokenDTO.token))

        }




    }
}