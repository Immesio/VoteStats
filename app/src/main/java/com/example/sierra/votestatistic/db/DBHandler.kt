package com.example.sierra.votestatistic.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.sierra.votestatistic.Classes.Question
import com.example.sierra.votestatistic.Interfaces.DBInterface
import java.util.ArrayList
class DBHandler (context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), DBInterface {
    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_IDINDB + " TEXT, " +
                KEY_QUESTION + " TEXT, " +
                KEY_COUNTCHOICE + " INTEGER, " +
                KEY_FIRST + " TEXT, " +
                KEY_SECOND + " TEXT, " +
                KEY_THIRD + " TEXT, " +
                KEY_FOURTH + " TEXT, " +
                KEY_FIFTH + " TEXT, " +
                KEY_RESULTFIRST + " INTEGER, " +
                KEY_RESULTSECOND + " INTEGER, " +
                KEY_RESULTTHIRD + " INTEGER, " +
                KEY_RESULTFOURTH + " INTEGER, " +
                KEY_RESULTFIFTH + " INTEGER);")

        db.execSQL("CREATE TABLE " + TABLE_NAME2 + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_IDINDB + " TEXT);")
        db.execSQL("CREATE TABLE " + TABLE_NAME3 + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_IDINDB + " TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);onCreate(db);
    }

    override fun addQuestion(question: Question) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_IDINDB, question.idInDB)
        values.put(KEY_QUESTION, question.question)
        values.put(KEY_COUNTCHOICE, question.countChoice)
        values.put(KEY_FIRST, question.first)
        values.put(KEY_SECOND, question.second)
        values.put(KEY_THIRD, question.third)
        values.put(KEY_FOURTH, question.fourth)
        values.put(KEY_FIFTH, question.fifth)
        values.put(KEY_RESULTFIRST, question.resultfirst)
        values.put(KEY_RESULTSECOND, question.resultsecond)
        values.put(KEY_RESULTTHIRD, question.resultthird)
        values.put(KEY_RESULTFOURTH, question.resultfourth)
        values.put(KEY_RESULTFIFTH, question.resultfifth)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    override fun addSkipedVoting(skipedID: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_IDINDB, skipedID)
        db.insert(TABLE_NAME2, null, values)
        db.close()
    }

    override fun hasSkiped(skipedID: String): Boolean{

        val selectQuery = "SELECT  * FROM $TABLE_NAME2"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(1).equals(skipedID))
                    return false
            } while (cursor.moveToNext())
        }
        return true
    }

   override fun getAllQuestion(): List<Question> {
        val QuestionList = ArrayList<Question>()
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val question = Question()
                question.id = Integer.parseInt(cursor.getString(0))
                question.idInDB = cursor.getString(1)
                question.question = cursor.getString(2)
                question.countChoice = Integer.parseInt(cursor.getString(3))
                question.first = cursor.getString(4)
                question.second = cursor.getString(5)
                question.third = cursor.getString(6)
                question.fourth = cursor.getString(7)
                question.fifth = cursor.getString(8)
                question.resultfirst = Integer.parseInt(cursor.getString(9))
                question.resultsecond = Integer.parseInt(cursor.getString(10))
                question.resultthird = Integer.parseInt(cursor.getString(11))
                question.resultfourth = Integer.parseInt(cursor.getString(12))
                question.resultfifth = Integer.parseInt(cursor.getString(13))
                QuestionList.add(question)
            } while (cursor.moveToNext())
        }

        return QuestionList
    }

    override fun deleteAllQuestion() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, null, null)
        db.close()
    }

    override fun getQuestionCount(): Int {
        val countQuery = "SELECT  * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(countQuery, null)
        return cursor.count
    }
    override fun addSkipedTMP(skipedID: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_IDINDB, skipedID)
        db.insert(TABLE_NAME3, null, values)
        db.close()
    }

    override fun deleteQuestion(id: String) {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE "+ KEY_IDINDB+"='"+id+"'");
        db.close();
    }

    override fun hasSkipedTMP(skipedID: String): Boolean{

        val selectQuery = "SELECT  * FROM $TABLE_NAME3"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(1).equals(skipedID))
                    return false
            } while (cursor.moveToNext())
        }
        return true
    }
    override fun deleteAllSkipedTMP() {
        val db = this.writableDatabase
        db.delete(TABLE_NAME3, null, null)
        db.close()
    }
    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Voting.db"

        val TABLE_NAME = "LoadVoting"
        val KEY_ID = "_id"
        val KEY_IDINDB = "_idindb"
        val KEY_QUESTION = "Question"
        val KEY_COUNTCHOICE = "CountChoice"
        val KEY_FIRST = "first"
        val KEY_SECOND = "second"
        val KEY_THIRD = "third"
        val KEY_FOURTH = "fourth"
        val KEY_FIFTH = "fifth"
        val KEY_RESULTFIRST = "resultfirst"
        val KEY_RESULTSECOND = "resultsecond"
        val KEY_RESULTTHIRD = "resultthird"
        val KEY_RESULTFOURTH = "resultfourth"
        val KEY_RESULTFIFTH = "resultfifth"

        val TABLE_NAME2 = "SkipedVoting"
        val TABLE_NAME3 = "TMPSkiped"
    }
}



