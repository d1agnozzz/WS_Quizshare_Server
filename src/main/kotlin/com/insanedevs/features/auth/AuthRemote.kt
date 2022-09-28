package com.insanedevs.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthReceiveRemote(
    val token: String
)

@Serializable
data class AuthResponseRemote(
    val email: String
)