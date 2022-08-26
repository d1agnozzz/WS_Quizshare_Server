package com.insanedevs.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table("users") {
    private val email = Users.varchar("email", 30)
    private val password = Users.varchar("password", 25)
    private val name = Users.varchar("name", 25)
    private val secondName = Users.varchar("second_name", 25)
    private val patronymicName = Users.varchar("patronymic_name", 25).nullable()

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[email] = userDTO.email
                it[password] = userDTO.password
                it[name] = userDTO.name
                it[secondName] = userDTO.secondName
                it[patronymicName] = userDTO.patronymicName
            }
        }
    }

    fun fetchUser(email: String): UserDTO? {
        return try {
            transaction {
                val userModel = Users.select { Users.email.eq(email) }.single()
                UserDTO(
                    email = userModel[Users.email],
                    password = userModel[password],
                    name = userModel[name],
                    secondName = userModel[secondName],
                    patronymicName = userModel[patronymicName]
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}