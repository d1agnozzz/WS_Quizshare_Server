package com.insanedevs.database.tokens

import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Tokens: IdTable<Int>("tokens") {
    override val id = Tokens.integer("id").autoIncrement().entityId()
    private val email = Tokens.varchar("email", 30)
    private val token = Tokens.varchar("token", 50)


    fun insert(tokenDTO: TokenDTO){
        transaction {
            Tokens.insert {
                it[email] = tokenDTO.email!!
                it[token] = tokenDTO.token
            }
        }
    }

    fun fetchToken(token: String): TokenDTO? {
        return try {
            transaction {
                val tokenModel = Tokens.select { Tokens.token.eq(token) }.single()
                TokenDTO(
                    email = tokenModel[email],
                    token = tokenModel[Tokens.token]
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}