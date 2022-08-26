package com.insanedevs.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val email: String,
    val name: String,
    val secondName: String,
    val patronymicName: String,
    val password: String
)

@Serializable
data class RegisterResponseRemote(
    val token: String
)