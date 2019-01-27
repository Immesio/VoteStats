package com.example.sierra.votestatistic.Classes

import android.view.View
import android.view.animation.DecelerateInterpolator

class Question {

    var id: Int = 0
    var idInDB: String=""
    var question: String=""
    var countChoice: Int = 0
    var first: String=""
    var second: String=""
    var third: String=""
    var fourth: String=""
    var fifth: String=""
    var resultfirst: Int = 0
    var resultsecond: Int = 0
    var resultthird: Int = 0
    var resultfourth: Int = 0
    var resultfifth: Int = 0
    private lateinit var requestBtnClickListener: View.OnClickListener
    constructor() {}

    constructor(id: Int, idInDB: String, Question: String, countChoice: Int, first: String, second: String, third: String, fourth: String, fifth: String, resultfirst: Int, resultsecond: Int, resultthird: Int, resultfourth: Int, resultfifth: Int) {
        this.id = id
        this.idInDB = idInDB
        this.question = Question
        this.countChoice = countChoice
        this.first = first
        this.second = second
        this.third = third
        this.fourth = fourth
        this.fifth = fifth
        this.resultfirst = resultfirst
        this.resultsecond = resultsecond
        this.resultthird = resultthird
        this.resultfourth = resultfourth
        this.resultfifth = resultfifth
    }

    constructor(idInDB: String, Question: String, countChoice: Int, first: String, second: String, third: String, fourth: String, fifth: String, resultfirst: Int, resultsecond: Int, resultthird: Int, resultfourth: Int, resultfifth: Int) {
        this.idInDB = idInDB
        this.question = Question
        this.countChoice = countChoice
        this.first = first
        this.second = second
        this.third = third
        this.fourth = fourth
        this.fifth = fifth
        this.resultfirst = resultfirst
        this.resultsecond = resultsecond
        this.resultthird = resultthird
        this.resultfourth = resultfourth
        this.resultfifth = resultfifth
    }

    fun getRequestBtnClickListener(): View.OnClickListener {
        return requestBtnClickListener
    }

    fun setRequestBtnClickListener(requestBtnClickListener: View.OnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener
    }

}




