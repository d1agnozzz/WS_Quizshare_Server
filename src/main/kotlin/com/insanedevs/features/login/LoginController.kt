package com.insanedevs.features.login

import com.insanedevs.database.tokens.TokenDTO
import com.insanedevs.database.tokens.Tokens
import com.insanedevs.database.users.Users
import com.insanedevs.features.LoginReceiveRemote
import com.insanedevs.features.LoginResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call: ApplicationCall) {
     suspend fun performLogin() {
         val receive = call.receive<LoginReceiveRemote>()

         val userDTO = Users.fetchUser(receive.email)

         if (userDTO == null) {
             call.respond(HttpStatusCode.BadRequest, "User not found")
         } else {
             if (userDTO.password == receive.password) {
                 var tokenDTO = TokenDTO(
                     email = userDTO.email,
                     token = UUID.randomUUID().toString()
                 )
                 while (Tokens.fetchToken(tokenDTO) != null) {
                     tokenDTO = TokenDTO(
                         email = userDTO.email,
                         token = UUID.randomUUID().toString()
                     )
                 }

                 Tokens.insert(tokenDTO)

                 call.respond(LoginResponseRemote(token = tokenDTO.token))
             } else {
                 call.respond(HttpStatusCode.BadRequest, "Invalid password")
             }
         }
     }
}