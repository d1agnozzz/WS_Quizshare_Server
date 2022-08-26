package com.insanedevs

import com.insanedevs.features.login.configureLoginRouting
import com.insanedevs.features.register.configureRegisterRouting
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import com.insanedevs.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {
    Database.connect("jdbc:postgresql://localhost:5432/quizshare", driver = "org.postgresql.Driver",
    user = "postgres", password = "admin")

    embeddedServer(CIO, port = 8080, host = "localhost") {
        configureRouting()
        configureSerialization()
        configureLoginRouting()
        configureRegisterRouting()
    }.start(wait = true)
}
