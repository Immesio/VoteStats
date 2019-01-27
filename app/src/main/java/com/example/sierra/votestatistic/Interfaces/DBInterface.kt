package com.example.sierra.votestatistic.Interfaces

import com.example.sierra.votestatistic.Classes.Question


interface DBInterface {
    fun getAllQuestion(): List<Question>
    fun getQuestionCount(): Int
    fun addQuestion(question: Question)
    fun deleteAllQuestion()
    fun deleteQuestion(id: String)

    fun deleteAllSkipedTMP()
    fun addSkipedTMP(skipedID : String)
    fun hasSkipedTMP(skipedID : String) : Boolean

    fun addSkipedVoting(skipedID : String)
    fun hasSkiped(skipedID : String) : Boolean
}
