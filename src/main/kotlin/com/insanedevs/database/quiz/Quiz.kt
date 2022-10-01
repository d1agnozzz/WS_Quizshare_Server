package com.insanedevs.database.quiz

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object Quiz : IdTable<Int>("quiz") {
    override val id = Quiz.integer("id").autoIncrement().entityId()
    private val creatorEmail = Quiz.varchar("creator_email", 30)
    private val group = Quiz.varchar("group", 30).nullable()
    private val title = Quiz.varchar("title", 30)
    private val description = Quiz.varchar("description", 255)
    private val quizType = Quiz.varchar("quiz_type", 10)

    fun insertAndGetId(quizDTO: QuizDTO): Int {
        val id = transaction {
            Quiz.insertAndGetId {
                it[creatorEmail] = quizDTO.creatorEmail
                it[group] = quizDTO.group
                it[title] = quizDTO.title
                it[description] = quizDTO.description
                it[quizType] = quizDTO.quizType
            }
        }
        return id.value
    }

    fun fetchQuiz(
        creatorEmail: String,
        group: String?,
        title: String,
        description: String,
        quizType: String
    ) : QuizDTO? {
        return try {
            val quizModel = Quiz.select {
                Quiz.creatorEmail.eq(
                    creatorEmail
                ) and Quiz.group.eq(
                    group
                ) and Quiz.title.eq(
                    title
                ) and Quiz.description.eq(
                    description
                ) and Quiz.quizType.eq(
                    quizType
                )
            }.single()
            QuizDTO(
                id = quizModel[Quiz.id].value,
                creatorEmail = quizModel[Quiz.creatorEmail],
                group = quizModel[Quiz.group],
                title = quizModel[Quiz.title],
                description = quizModel[Quiz.description],
                quizType = quizModel[Quiz.quizType]
            )
        } catch (e:NoSuchElementException) {
            null
        }

    }
}